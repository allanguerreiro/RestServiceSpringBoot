package org.rest.resource;

import org.rest.exception.SchoolNotFoundException;
import org.rest.model.School;
import org.rest.persistence.SchoolDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by allan on 21/11/16.
 */
@Path("/schools")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class SchoolResource {
    private final Logger log = LoggerFactory.getLogger(SchoolResource.class.getName());
    private final SchoolDao schoolDao;

    @Inject
    public SchoolResource(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @GET
    public List<School> getAll() {
        return this.schoolDao.findAll();
    }

    @POST
//    @Path("/authenticate/login={login}&password={password}")
    @Path("/authenticate")
    public School authenticate(@HeaderParam("login") String login, @HeaderParam("password") String password) {
        School school = new School();
        log.info("O username é: " + login + " e a Senha é: " + password);
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            school = this.existSchool(login, password);
            if (school != null && !school.equals("")) {
                school.setAuthenticated(true);
                school.setMessage("Login Ok");
                school = new School(school.getName(), school.getAddress(), school.getCardsLevelOne(),
                        school.getCardsLevelTwo(), school.getCardsLevelThree(), school.getAuthenticated(),
                        school.getMessage());
            } else {
                school = new School();
                school.setAuthenticated(false);
                school.setMessage("Login inválido");
            }
        }

        return school;
    }

    private School existSchool(String login, String password) {
        School school = this.schoolDao.findSchoolByMecCodeAndPassword(login, MD5(password));
        if (school == null || school.equals("")) {
            new SchoolNotFoundException(login, MD5(password));
        }
        return school;
    }

    private String MD5(String password) {
        try {
            log.info("O password é: " + password);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] array = md5.digest(password.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            log.info("O password encriptado é: " + stringBuffer.toString());
            return stringBuffer.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.getStackTrace();
        }
        return null;
    }
}

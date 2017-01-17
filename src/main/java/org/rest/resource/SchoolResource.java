package org.rest.resource;

import org.modelmapper.ModelMapper;
import org.rest.dto.SchoolDto;
import org.rest.exception.SchoolNotFoundException;
import org.rest.model.School;
import org.rest.persistence.SchoolDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 21/11/16.
 */
@RestController
@RequestMapping("/schools")
@Component
public class SchoolResource {
    private final Logger log = LoggerFactory.getLogger(SchoolResource.class.getName());
    private final SchoolDao schoolDao;
    private final ModelMapper mapper;

    @Autowired
    public SchoolResource(SchoolDao schoolDao, ModelMapper mapper) {
        this.schoolDao = schoolDao;
        this.mapper = mapper;
    }

    @RequestMapping
    public List<SchoolDto> getAll() {
        List<School> schools;
        schools = schoolDao.findAll();
        List<SchoolDto> dtos = new ArrayList<>();

        for (School school : schools) {
            final SchoolDto schoolDto = mapper.map(school, SchoolDto.class);
            dtos.add(schoolDto);
        }

        return dtos;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public SchoolDto authenticate(@RequestHeader("login") String login, @RequestHeader("password") String password) {
        School school;
        SchoolDto schoolDto = new SchoolDto();
        log.info("O username é: " + login + " e a Senha é: " + password);
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            school = this.existSchool(login, password);
            if (school != null && !school.equals("")) {
                schoolDto = mapper.map(school, SchoolDto.class);

                schoolDto.setAuthenticated(true);
                schoolDto.setMessage("Login Ok");
            } else {
                schoolDto.setAuthenticated(false);
                schoolDto.setMessage("Login inválido");
            }
        }

        return schoolDto;
    }

    private School existSchool(String login, String password) {
        School school = schoolDao.findSchoolByMecCodeAndPassword(login, MD5(password));
        if (school == null || school.equals("")) {
            new SchoolNotFoundException(login, MD5(password));
        }
        return school;
    }

    // FIXME Deverá ser mantido até que a FCC implemente em seu código.
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

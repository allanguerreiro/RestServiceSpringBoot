package org.rest.persistence;

import org.rest.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by allan on 21/11/16.
 */
public interface SchoolDao extends JpaRepository<School, Long> {

    School findSchoolByMecCode(String mecCode);

    School findSchoolByMecCodeAndPassword(String mecCode, String password);
}

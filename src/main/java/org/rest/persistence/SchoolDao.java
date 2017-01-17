package org.rest.persistence;

import org.rest.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by allan on 21/11/16.
 */
@Repository
public interface SchoolDao extends JpaRepository<School, Long> {

    @Query(value = "SELECT school FROM School school WHERE mecCode = ?1")
    School findSchoolByMecCode(String mecCode);

    @Query(value = "SELECT * FROM OBMEP_ESCOLA WHERE CD_MEC_ESCOLA = ?1 AND NM_SENHA = ?2", nativeQuery = true)
    School findSchoolByMecCodeAndPassword(String mecCode, String password);

    @Query(value = "SELECT * FROM OBMEP_ESCOLA LIMIT 10", nativeQuery = true)
    List<School> findAll();
}

package org.rest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by allan on 21/11/16.
 */
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "OBMEP_ESCOLA")
public class School {

    @Id
    @Column(name = "CD_ESCOLA")
    @Getter @Setter
    private Long id;

    @Column(name = "NM_ESCOLA")
    @Getter @Setter
    private String name;

    @Column(name = "NM_ENDERECO")
    @Getter @Setter
    private String address;

    @Column(name = "NIVEL1_NR_ALUNOS")
    @Getter @Setter
    private Integer cardsLevelOne;

    @Column(name = "NIVEL2_NR_ALUNOS")
    @Getter @Setter
    private Integer cardsLevelTwo;

    @Column(name = "NIVEL3_NR_ALUNOS")
    @Getter @Setter
    private Integer cardsLevelThree;

    @Getter @Setter
    private transient Boolean authenticated;

    @Getter @Setter
    private transient String message;

    @Column(name="CD_MEC_ESCOLA")
    @Getter @Setter
    private String mecCode;

    @Column(name="NM_SENHA")
    @Getter @Setter
    private String password;


    public School(String name, String address, Integer cardsLevelOne,
                  Integer cardsLevelTwo, Integer cardsLevelThree,
                  Boolean authenticated, String message) {
        this.name = name;
        this.address = address;
        this.cardsLevelOne = cardsLevelOne;
        this.cardsLevelTwo = cardsLevelTwo;
        this.cardsLevelThree = cardsLevelThree;
        this.authenticated = authenticated;
        this.message = message;
    }
}

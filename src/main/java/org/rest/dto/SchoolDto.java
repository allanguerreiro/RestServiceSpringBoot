package org.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by allan on 11/01/17.
 */
@EqualsAndHashCode
@ToString
@JsonPropertyOrder(value = {"id", "name", "address", "cardsLevelOne", "cardsLevelTwo", "cardsLevelThree"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolDto implements Serializable {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private Integer cardsLevelOne;

    @Getter @Setter
    private Integer cardsLevelTwo;

    @Getter @Setter
    private Integer cardsLevelThree;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private Boolean authenticated;
}

package org.rest.mapping;

import org.modelmapper.AbstractConverter;
import org.rest.dto.SchoolDto;
import org.rest.model.School;
import org.springframework.stereotype.Component;

/**
 * Created by allan on 11/01/17.
 */
@Component
public class SchoolMapping extends AbstractConverter<School, SchoolDto> {

    @Override
    protected SchoolDto convert(School school) {
        SchoolDto schoolDto = new SchoolDto();

        schoolDto.setId(school.getId());
        schoolDto.setName(school.getName());
        schoolDto.setAddress(school.getAddress());
        schoolDto.setCardsLevelOne(school.getCardsLevelOne());
        schoolDto.setCardsLevelTwo(school.getCardsLevelTwo());
        schoolDto.setCardsLevelThree(school.getCardsLevelThree());

        return schoolDto;
    }
}

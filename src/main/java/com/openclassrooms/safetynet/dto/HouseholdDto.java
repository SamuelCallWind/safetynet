package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class HouseholdDto {
    List<PersonDto> personDtos;

    public HouseholdDto(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }
}

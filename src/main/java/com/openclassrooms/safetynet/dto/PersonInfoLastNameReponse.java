package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoLastNameReponse {

    List<PersonInfoLastNameDto> persons;

    public PersonInfoLastNameReponse(List<PersonInfoLastNameDto> persons) {
        this.persons = persons;
    }
}

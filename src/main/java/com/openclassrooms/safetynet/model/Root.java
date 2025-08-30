package com.openclassrooms.safetynet.model;

import lombok.Data;

import java.util.List;

@Data
public class Root {

    private List<Person> persons;
    private List<Medicalrecord> medicalrecords;
    private List<Firestation> firestations;

}

package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FirestationResponse {

    private List<FirestationPerson> listPersons;
    private int numberOfAdults;
    private int numberOfChildren;

    public FirestationResponse(List<FirestationPerson> listPersons, int numberOfAdults, int numberOfChildren) {
        this.listPersons = listPersons;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }
}

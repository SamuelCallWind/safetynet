package com.openclassrooms.safetynet.dto;

import lombok.Data;

@Data
public class FirestationPerson {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public FirestationPerson(String name, String lastName, String address, String phone) {
        this.firstName = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}

package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FloodPersonDto {

    private String firstName;
    private String lastName;
    private List<String> medications;
    private List<String> allergies;
    private String phoneNumber;
    private int age;

    public FloodPersonDto(String firstName, String lastName, List<String> medications, List<String> allergies, String phoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.medications = medications;
        this.allergies = allergies;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
}

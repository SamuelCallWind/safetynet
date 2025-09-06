package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Medicalrecord;
import lombok.Data;

import java.util.List;

@Data
public class PersonDto {

    private String firstName;
    private String lastName;
    private List<String> medications;
    private List<String> allergies;
    private String phoneNumber;
    private int age;

    public PersonDto(String firstName, String lastName, List<String> medications, List<String> allergies, String phoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.medications = medications;
        this.allergies = allergies;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
}

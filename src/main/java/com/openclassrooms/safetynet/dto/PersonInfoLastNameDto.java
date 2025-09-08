package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class PersonInfoLastNameDto {
    String firstName;
    String lastName;
    String address;
    int age;
    String email;
    List<String> medications;
    List<String> allergies;

    public PersonInfoLastNameDto(String firstName, String lastName, String address, int age, String email, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }
}

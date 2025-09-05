package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Firestation;
import lombok.Data;

import java.util.List;

@Data
public class FirePersonDto {

    String firstName;
    String lastName;
    String phoneNumber;
    int age;
    List<String> medications;
    List<String> allergies;
    int firestationAssigned;

    public FirePersonDto(String firstName, String lastName, String phoneNumber, int age, List<String> medications,
                         List<String> allergies, int firestationAssigned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
        this.firestationAssigned = firestationAssigned;
    }
}

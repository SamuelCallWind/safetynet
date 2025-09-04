package com.openclassrooms.safetynet.dto;

import lombok.Data;

@Data
public class ChildDto {
    private String firstName;
    private String lastName;
    private int age;

    public ChildDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}

package com.openclassrooms.safetynet.dto;

import lombok.Data;

@Data
public class HouseholdMemberDto {
    private String firstName;
    private String lastName;

    public HouseholdMemberDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

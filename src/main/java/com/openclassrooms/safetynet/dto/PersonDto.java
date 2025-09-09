package com.openclassrooms.safetynet.dto;

import lombok.Data;

@Data
public class PersonDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private String email;
    private int zip;
}

package com.openclassrooms.safetynet.model;

import lombok.Data;

@Data
public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private String email;
    private int zip;

    public Person() { }

    public Person(String firstName, String lastName, String address, String city, String phone, String email, int zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.zip = zip;
    }
}

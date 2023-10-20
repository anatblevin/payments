package com.example.payments.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDetails {
    private Name name;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String ssn;
}

package com.example.payments.model;

import lombok.Data;

@Data
public class Address {
    private String streetAddress;
    private String city;
    private USState state;
    private String postalCode;
    private String country;
}

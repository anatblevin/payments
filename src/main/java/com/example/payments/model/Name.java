package com.example.payments.model;

import lombok.Data;

@Data
public class Name {
    private String givenName;
    private String familyName;
    private String title;
    private String suffix;
    private String middleName;
}

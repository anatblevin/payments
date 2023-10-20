package com.example.payments.model;

import lombok.Data;

@Data
public class BeneficialOwners {
    private PersonDetails personDetails;
    private Double percentageOwnership;
    private String DOC; // dont i have it in person? duplicate ?
}

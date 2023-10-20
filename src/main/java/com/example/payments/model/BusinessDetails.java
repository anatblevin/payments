package com.example.payments.model;

import lombok.Data;

@Data
public class BusinessDetails {
    private String legalBusinessName;
    private String identificationNumber;
    private BusinessType businessType;
    private String businessPhone;
    private Address businessAddress;
    private PersonDetails primaryAuthorizedPerson; /*Person authorized to act on behalf of the business.
                                                     If a Primary Authorized Person owns or controls at least 25% of the business,
                                                     they are also automatically added as a Beneficial Owner. */
}

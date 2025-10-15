package com.kawaiicanvas.kawaicanvas.Customer;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Customer {

    private String firstName;
    private String lastName;
    private String shippingAddress;
    private String shippingCity;
    private String postalCode;
    private int phoneNumber;

}

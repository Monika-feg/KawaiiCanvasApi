package com.kawaiicanvas.kawaicanvas.Customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// info om kunden inför en beställning
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Customer {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String shippingAddress;
    @NotBlank
    private String shippingCity;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;

}

package com.kawaiicanvas.kawaicanvas.Payment;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private String orderId;
    private String stripePaymentId;
    private String paymentStatus;
    private BigDecimal amount;
    private String url;

}

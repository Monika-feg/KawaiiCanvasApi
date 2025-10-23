package com.kawaiicanvas.kawaicanvas.Canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "canvases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Canvas {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String price;
    private String imageUrl;

    @DocumentReference
    @JsonIgnore // Förhindrar serialisering av cart-referensen
    private Cart cart;

}

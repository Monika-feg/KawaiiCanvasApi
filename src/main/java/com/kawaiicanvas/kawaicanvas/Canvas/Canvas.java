package com.kawaiicanvas.kawaicanvas.Canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kawaiicanvas.kawaicanvas.Cart.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "canvases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canvas {

    @Id
    private String id;
    private String title;
    private String price;

    @DocumentReference
    @JsonIgnore // Förhindrar serialisering av cart-referensen
    private Cart cart;

}

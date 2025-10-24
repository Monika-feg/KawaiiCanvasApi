package com.kawaiicanvas.kawaicanvas.Cart.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String id;
    @DocumentReference
    private Canvas canvas;
    private int numberOfCanvases;

}

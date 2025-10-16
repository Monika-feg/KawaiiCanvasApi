package com.kawaiicanvas.kawaicanvas.Websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateMessage {

    private String cartId;
    private int canvasCount;

}

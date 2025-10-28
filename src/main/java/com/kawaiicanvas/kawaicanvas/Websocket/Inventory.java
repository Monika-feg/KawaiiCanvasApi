package com.kawaiicanvas.kawaicanvas.Websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private String itemId;
    private String itemName;
    private int quantity;

}

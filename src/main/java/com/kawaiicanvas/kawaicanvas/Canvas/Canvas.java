package com.kawaiicanvas.kawaicanvas.Canvas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String description;
    private String price;

}

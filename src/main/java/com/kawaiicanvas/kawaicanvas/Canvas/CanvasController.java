package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/canvas")
public class CanvasController {

    @Autowired
    private CanvasService canvasService;

    @GetMapping()
    public List<Canvas> getAllCanvas() {
        return canvasService.getAllCanvas();
    }

}

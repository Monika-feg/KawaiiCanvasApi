package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // för admin att lägga till nya canvas ( gör en admin inloggning senare)
    @PostMapping()
    public Canvas createNewCanvas(@RequestBody Canvas canvas) {
        return canvasService.createNewCanvas(canvas);
    }

    // bara för Admin att ta bort canvas (gör en admin inloggning senare)
    @DeleteMapping("/{id}")
    public String deleteCanvas(@PathVariable String id) {
        canvasService.deleteCanvas(id);
        return "Canvas with id " + id + " has been deleted.";
    }

}

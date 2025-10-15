package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

// ser ut såhär så länge ska så småningom ha felhanterare
@Service
@AllArgsConstructor
public class CanvasService {

    private final CanvasRepository canvasRepository;

    public List<Canvas> getAllCanvas() {
        return canvasRepository.findAll();
    }

    public Canvas getCanvasById(String id) {
        return canvasRepository.findById(id).orElse(null);
    }

    public Canvas createNewCanvas(Canvas canvas) {
        return canvasRepository.save(canvas);
    }

    public void deleteCanvas(String id) {
        canvasRepository.deleteById(id);
    }

}

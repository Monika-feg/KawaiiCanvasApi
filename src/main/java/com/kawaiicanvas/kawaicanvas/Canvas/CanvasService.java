package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CanvasService {

    private final CanvasRepository canvasRepository;

    public List<Canvas> getAllCanvas() {
        return canvasRepository.findAll();
    }

}

package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

// ser ut såhär så länge ska så småningom ha felhanterare
@Service
@AllArgsConstructor
public class CanvasService {

    private final CanvasRepository canvasRepository;

    // hämtar alla tavlor
    public List<Canvas> getAllCanvas() {
        try {
            return canvasRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Canvas data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not find Canvases ", e);
        }

    }

    // hämtar en tavla med id
    public Canvas getCanvasById(String id) {
        try {
            return canvasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find canvas with id: " + id));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Canvas data is invalid ", e);
        }

    }

    // skapar en ny tavla( endast för admin)
    public Canvas createNewCanvas(Canvas canvas) {
        try {
            return canvasRepository.save(canvas);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Canvas data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not create canvas ", e);
        }

    }

    // tar bort en tavla med id ( endast för admin)
    public void deleteCanvas(String id) {
        try {
            canvasRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Canvas data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not delete canvas with id: " + id, e);
        }

    }

}

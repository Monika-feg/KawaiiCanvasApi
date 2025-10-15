package com.kawaiicanvas.kawaicanvas.Canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Använder Mockito för att skapa mock-objekt och injicera dem i CanvasService
@ExtendWith(MockitoExtension.class)
public class CanvasServiceTest {
    // tog lite här ifrån https://www.youtube.com/watch?v=IZa-KRQzXpg
    // testade mig fram och fick lite hjälp av Chatgpt( vilka @ jag skulle ha med
    // för att få det att fungera)

    // Skapar en instans av CanvasService och injicerar mockade beroenden
    // automatiskt
    @InjectMocks
    private CanvasService canvasService;

    // Mockar CanvasRepository för att simulera databasinteraktioner
    @Mock
    private CanvasRepository canvasRepository;

    // detta test ska spara en ny canvas
    @Test
    public void saveCanvasTest() {
        // skapar en ny canvas
        Canvas canvas = new Canvas();
        canvas.setTitle("Test Canvas");
        canvas.setPrice("100");

        // Mockar save-metoden i repository: returnera samma Canvas-objekt som skickas
        // in
        when(canvasRepository.save(any(Canvas.class))).thenReturn(canvas);
        Canvas received = canvasService.createNewCanvas(canvas);

        // Kontrollera att Canvas-objektet returnerat av service-metoden har rätt värden
        assertEquals("Test Canvas", received.getTitle());
        assertEquals("100", received.getPrice());
    }

}

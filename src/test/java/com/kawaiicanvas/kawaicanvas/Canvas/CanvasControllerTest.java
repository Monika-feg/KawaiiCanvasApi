package com.kawaiicanvas.kawaicanvas.Canvas;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// testade mig fram, fick lite hjälp av Chatgpt ( vilka @ jag skulle ha med för att få det att fungera)
// tog även hjälp från tidigare projekt jag varit med i
@WebMvcTest(CanvasController.class)
public class CanvasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CanvasService canvasService;

    // detta test ska hämta en canvas med ett specifikt id
    @Test
    public void getCanvasByIdTest() throws Exception {
        // skapar ett nytt canvas objekt
        Canvas canvas = new Canvas();
        canvas.setId("1");
        canvas.setTitle("Test Canvas");
        canvas.setPrice("100");

        // mockar service-svar
        when(canvasService.getCanvasById("1")).thenReturn(canvas);

        // utför GET-förfrågan och verifierar svaret
        mockMvc.perform(MockMvcRequestBuilders.get("/api/canvas/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Canvas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("100"));
    }
}
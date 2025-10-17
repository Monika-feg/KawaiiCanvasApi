package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

// för felhantering har jag inspirerats av dessa
// https://www.baeldung.com/spring-rest-openapi-documentation
// https://www.baeldung.com/swagger-operation-vs-apiresponse
@RestController
@RequestMapping("/api/canvas")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class CanvasController {

    @Autowired
    private CanvasService canvasService;

    @GetMapping()
    public ResponseEntity<KawaiiResponse<List<Canvas>>> getAllCanvas() {
        try {
            List<Canvas> canvases = canvasService.getAllCanvas();
            return ResponseEntity.ok(KawaiiResponse.success("Canvas retrieved successfully", canvases));
        } catch (IllegalAccessError e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<KawaiiResponse<Canvas>> getCanvasById(@PathVariable String id) {
        try {
            Canvas canvas = canvasService.getCanvasById(id);
            return ResponseEntity.ok(KawaiiResponse.success("Canvas retrieved successfully", canvas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

    // för admin att lägga till nya canvas ( gör en admin inloggning senare)
    @PostMapping()
    public ResponseEntity<KawaiiResponse<Canvas>> createNewCanvas(@RequestBody Canvas canvas) {
        try {
            Canvas createdCanvas = canvasService.createNewCanvas(canvas);
            return ResponseEntity.ok(KawaiiResponse.success("Canvas created successfully", createdCanvas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

    // bara för Admin att ta bort canvas (gör en admin inloggning senare)
    @DeleteMapping("/{id}")
    public ResponseEntity<KawaiiResponse<String>> deleteCanvas(@PathVariable String id) {
        try {
            canvasService.deleteCanvas(id);
            return ResponseEntity.ok(
                    KawaiiResponse.success("Canvas deleted successfully",
                            "Canvas with id " + id + " has been deleted."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

}
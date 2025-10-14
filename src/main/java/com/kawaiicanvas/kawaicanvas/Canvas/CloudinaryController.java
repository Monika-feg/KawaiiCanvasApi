package com.kawaiicanvas.kawaicanvas.Canvas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;

@RestController
public class CloudinaryController {

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/gallery")
    public List<String> getImageCanvas(@RequestParam List<String> publicIds) {
        return publicIds.stream()
                .map(publicId -> cloudinary.url().secure(true).generate(publicId))
                .toList();
    }

}

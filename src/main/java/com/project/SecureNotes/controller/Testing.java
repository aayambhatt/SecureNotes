package com.project.SecureNotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {

    @GetMapping("/anything")
    public String getAnything(){
        return "Anything";
    }
}

package com.pooh.notebook.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class TestController {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String getTest(){
        return "Hello get";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String PostTest(){
        return "Hello post";
    }
}

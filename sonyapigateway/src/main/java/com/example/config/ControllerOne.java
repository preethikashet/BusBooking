package com.example.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kpis")
public class ControllerOne {
    @GetMapping("/testOne")
    public String testOne() {
        return "JWT is working!!!";
    }
}

// src/main/java/com/example/demo/GreetingController.java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping("/greet")
    public String greet() {
        return "Hello, World";
    }
}

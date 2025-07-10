// src/test/java/com/example/demo/GreetingServiceTest.java
package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingServiceTest {
    @Test
    void testGreet() {
        GreetingService service = new GreetingService();
        String result = service.greet("World");
        assertEquals("Hello, World", result);
    }
}

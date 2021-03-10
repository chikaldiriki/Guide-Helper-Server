package server;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping
    public String get() {
        return "GET request!";
    }

    @PostMapping
    public String post() {
        return "POST request!";
    }

    @PutMapping
    public String put() {
        return "PUT request!";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE request";
    }
}
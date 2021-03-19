package server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.dto.UserDTO;

@RestController
public class UserController {

    @PostMapping("users/")
    void addUser() {
    }

    @GetMapping("users/{userId}")
    public UserDTO getUser(Long userId) {
        return new UserDTO();
    }
}

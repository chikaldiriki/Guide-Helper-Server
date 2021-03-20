package server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.core.dto.UserDTO;
import server.core.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUser(String userId) {
        return userService.getUser(userId);
    }
}

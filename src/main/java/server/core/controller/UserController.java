package server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.dto.UserDTO;
import server.core.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
}

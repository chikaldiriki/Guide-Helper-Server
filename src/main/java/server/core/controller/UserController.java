package server.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.dto.UserDTO;
import server.core.service.UserService;

@Tag(name = "User Controller", description = "Контроллер пользователей")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Добавить пользователя")
    @PostMapping("")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Получить токен по почте пользователя")
    @GetMapping("/token/{userMail}")
    public String getToken(@PathVariable String userMail) {
        return userService.getTokenById(userMail);
    }

    @Operation(summary = "Обновить токен пользователя")
    @PutMapping("/token/{userMail}/{token}")
    public void updateToken(@PathVariable String userMail, @PathVariable String token) {
        userService.updateTokenById(userMail, token);
    }

    @Operation(summary = "Изменить пользователя по id")
    @PutMapping("/{userId}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable String userId) {
        userService.updateUser(userDTO, userId);
    }

    @Operation(summary = "Сделать пользователя гидом")
    @PutMapping("/guide_creation/{userId}")
    public void setGuideStatus(@PathVariable String userId) {
        userService.setGuideStatus(userId);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/delete/user_mail={userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

}

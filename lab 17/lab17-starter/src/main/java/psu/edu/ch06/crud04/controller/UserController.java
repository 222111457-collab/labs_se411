
package psu.edu.ch06.crud04.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psu.edu.ch06.crud04.model.User;
import psu.edu.ch06.crud04.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody User user) {

        User saved = userService.register(user);

        return ResponseEntity.ok(
            new User(
                saved.id(),
                saved.username(),
                ""
            )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody User user) {

        try {
            return ResponseEntity.ok(
                userService.verify(user)
            );

        } catch (AuthenticationException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
        }
    }
}

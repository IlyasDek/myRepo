package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    // TODO: Implement authentication endpoint and other user-related endpoints.
}
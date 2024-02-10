package com.rental.controller;

import com.rental.entities.User;
import com.rental.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
        }

        // Assuming you handle the creation logic in UserService
        User registeredUser = userService.registerUser(newUser);
        
        if (registeredUser == null) {
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // Assuming you do not want to return the password
        registeredUser.setPassword(null);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    
    // Add other CRUD operations as needed...
}

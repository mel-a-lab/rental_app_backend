package com.rental.controllers;

import com.rental.entities.User;
import com.rental.model.request.RegisterReq;
import com.rental.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtEncoder jwtEncoder;

    public UserController(UserService userService, JwtEncoder jwtEncoder) {
        this.userService = userService;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterReq registerReq) {
        if (userService.existsByEmail(registerReq.getEmail())) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
        }

        // Assuming you handle the creation logic in UserService
        User registeredUser = userService.registerUser(convertToUser(registerReq));

        if (registeredUser == null) {
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Assuming you do not want to return the password
        registeredUser.setPassword(null);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    private User convertToUser(RegisterReq registerReq) {
        User user = new User();
        user.setName(registerReq.getName());
        user.setEmail(registerReq.getEmail());
        String encodedPassword = jwtEncoder.encode(...);
        user.setPassword(registerReq.getPassword());
        return user;
    }


    // Add other CRUD operations as needed...
}

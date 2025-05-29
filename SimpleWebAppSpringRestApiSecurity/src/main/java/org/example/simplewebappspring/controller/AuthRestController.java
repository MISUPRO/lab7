package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.UserEntity;
import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.repository.AdminRepository;
import org.example.simplewebappspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;

    //Для входа
    public static class LoginRequest {
        public String name;
        public String pass;
    }

    //Для регистрации
    public static class RegisterRequest {
        public String name;
        public String pass;
        public String mail;
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AdminEntity admin = adminRepo.findByName(request.name).orElse(null);
        UserEntity user = userRepo.findByName(request.name).orElse(null);

        Map<String, Object> response = new HashMap<>();

        if (admin != null && admin.getPassword().equals(request.pass)) {
            response.put("role", "admin");
            response.put("name", admin.getName());
            return ResponseEntity.ok(response);
        } else if (user != null && user.getPassword().equals(request.pass)) {
            response.put("role", "user");
            response.put("name", user.getName());
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean userExists = userRepo.findByName(request.name).isPresent();
        boolean adminExists = adminRepo.findByName(request.name).isPresent();

        if (userExists || adminExists) {
            return ResponseEntity.status(409).body(Map.of("error", "User with this name already exists"));
        }

        UserEntity user = new UserEntity();
        user.setName(request.name);
        user.setPassword(request.pass);
        user.setEmail(request.mail);
        userRepo.save(user);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
}

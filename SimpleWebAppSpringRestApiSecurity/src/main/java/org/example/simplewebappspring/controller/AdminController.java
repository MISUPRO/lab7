package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Используем PasswordEncoder для хэширования пароля

    // Регистрация нового администратора через API
    @PostMapping("/register/admin")
    public ResponseEntity<AdminEntity> registerAdmin(@RequestBody AdminEntity adminEntity) {
        // Проверка, существует ли администратор с таким именем
        boolean adminExists = adminRepo.findByName(adminEntity.getName()).isPresent();

        if (adminExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Если администратор уже существует
        }

        // Хэшируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(encodedPassword);

        adminRepo.resetAutoIncrement();
        adminRepo.save(adminEntity);  // Сохраняем администратора в базу данных

        return ResponseEntity.status(HttpStatus.CREATED).body(adminEntity);  // Возвращаем успешный ответ
    }
}

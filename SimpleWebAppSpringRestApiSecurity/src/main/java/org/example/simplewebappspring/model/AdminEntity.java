package org.example.simplewebappspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins", schema = "user")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Автоинкрементный идентификатор

    @Column(nullable = false)//, unique = true)  // Уникальное имя администратора
    private String name;

    @Column(nullable = false)  // Пароль должен быть обязательным
    private String password;

    @Column(nullable = false)//, unique = true)  // Уникальный email пользователя
    private String email;

    @Column(nullable = false)  // Роль по умолчанию - "user"
    private String role = "admin";

    // Конструктор по умолчанию
    public AdminEntity() {}

    // Конструктор с параметрами (по желанию)
    public AdminEntity(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

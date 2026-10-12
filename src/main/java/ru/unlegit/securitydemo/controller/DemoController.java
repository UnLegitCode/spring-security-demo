package ru.unlegit.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Привет!!!";
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String userEndpoint() {
        return "Профиль пользователя";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/dashboard")
    public String adminEndpoint() {
        return "Панель администратора";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String home() {
        return "Домашняя страница";
    }
}
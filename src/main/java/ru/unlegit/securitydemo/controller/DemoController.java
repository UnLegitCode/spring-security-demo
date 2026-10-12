package ru.unlegit.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Привет!!!";
    }

    @GetMapping("/user/profile")
    public String userEndpoint() {
        return "Профиль пользователя";
    }

    @GetMapping("/admin/dashboard")
    public String adminEndpoint() {
        return "Панель администратора";
    }

    @GetMapping("/home")
    public String home() {
        return "Домашняя страница";
    }
}
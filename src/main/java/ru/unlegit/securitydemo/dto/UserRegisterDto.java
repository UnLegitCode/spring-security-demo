package ru.unlegit.securitydemo.dto;

import ru.unlegit.securitydemo.model.User;

public record UserRegisterDto(String username, String password, User.Role role) {}
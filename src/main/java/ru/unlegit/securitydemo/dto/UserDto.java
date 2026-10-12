package ru.unlegit.securitydemo.dto;

import ru.unlegit.securitydemo.model.User;

import java.time.LocalDateTime;

public record UserDto(int id, String username, User.Role role, LocalDateTime createdAt) {}
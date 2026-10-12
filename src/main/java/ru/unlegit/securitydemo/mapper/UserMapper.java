package ru.unlegit.securitydemo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.unlegit.securitydemo.dto.UserDto;
import ru.unlegit.securitydemo.dto.UserRegisterDto;
import ru.unlegit.securitydemo.model.User;

@Component
@AllArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserDto modelToDto(User model) {
        return new UserDto(model.getId(), model.getUsername(), model.getRole(), model.getCreatedAt());
    }

    public User registerDtoToModel(UserRegisterDto dto) {
        var model = new User();

        model.setUsername(dto.username());
        model.setPasswordHash(passwordEncoder.encode(dto.password()));
        model.setRole(dto.role());

        return model;
    }
}
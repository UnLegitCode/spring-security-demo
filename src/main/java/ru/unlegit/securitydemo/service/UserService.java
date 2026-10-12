package ru.unlegit.securitydemo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.unlegit.securitydemo.dto.UserDto;
import ru.unlegit.securitydemo.dto.UserRegisterDto;
import ru.unlegit.securitydemo.mapper.UserMapper;
import ru.unlegit.securitydemo.model.User;
import ru.unlegit.securitydemo.repository.UserRepository;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username: ".concat(username)));
    }

    public UserDto registerUser(UserRegisterDto dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        var model = userRepository.save(userMapper.registerDtoToModel(dto));

        return userMapper.modelToDto(model);
    }

    public void logoutUser(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) {
        var logoutHandler = new SecurityContextLogoutHandler();

        logoutHandler.logout(request, response, authentication);
    }

    public UserDto getUserInfo(Authentication authentication) {
        var user = (User) authentication.getPrincipal();

        log.info("Principal: {}", user);

        return userMapper.modelToDto(Objects.requireNonNull(user));
    }
}
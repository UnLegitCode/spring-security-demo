package ru.unlegit.securitydemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.unlegit.securitydemo.dto.UserRegisterDto;
import ru.unlegit.securitydemo.model.User;
import ru.unlegit.securitydemo.service.UserService;

@Slf4j
@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new UserRegisterDto("", "", User.Role.USER));

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("form") UserRegisterDto dto) {
        log.info("Registering: {}", dto);

        userService.registerUser(dto);

        return "redirect:/user/profile";
    }

    @PostMapping("/logout")
    public void logout(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) {
        userService.logoutUser(request, response, authentication);
    }
}
package ru.unlegit.securitydemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.unlegit.securitydemo.service.UserService;

@Controller
@AllArgsConstructor
public class DemoController {

    private final UserService userService;

    @GetMapping("/user/profile")
    public String userProfile(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUserInfo(authentication));

        return "user_profile";
    }
}
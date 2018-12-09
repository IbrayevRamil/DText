package com.dtproject.controllers;

import com.dtproject.services.SecurityUtils;
import com.dtproject.dtos.RegistrationDto;
import com.dtproject.models.User;
import com.dtproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    AuthenticationTrustResolver trustResolver;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getLoginPage() {
        if (SecurityUtils.isCurrentAuthenticationAnonymous(trustResolver)) {
            return "login";
        } else {
            return "redirect:/upload";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        synchronized (userRepository) {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                userRepository.save(new User(username, password));
                return "redirect:/login?register";
            } else {
                return "redirect:/register?error";
            }
        }
    }
}

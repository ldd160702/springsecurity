package com.example.springsecurity.controller;

import com.example.springsecurity.model.UserEntity;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class UserController {
    @Autowired
    private UserDetailsServiceImpl service;

    @RequestMapping("/user/me")
    public ResponseEntity<UserEntity> getUserInformation(Authentication authentication, Model model) {
        String username = authentication.getName();

        UserEntity user =  service.getUserByUsername(username);

        if (user == null) {
            model.addAttribute("message", "User does not exist");
            throw new RuntimeException("User does not exist");
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/admin/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public RedirectView deleteUser(@PathVariable String username, RedirectAttributes redirectAttributes) {
        UserEntity user = service.getUserByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "User does not exist");
            return new RedirectView("/admin");
        }

        service.deleteUserByUsername(username);

        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return new RedirectView("/admin");
    }



    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}

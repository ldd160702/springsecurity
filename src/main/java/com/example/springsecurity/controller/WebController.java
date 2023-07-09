package com.example.springsecurity.controller;

import com.example.springsecurity.model.UserEntity;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import com.sun.xml.bind.util.AttributesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserDetailsServiceImpl service;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView homepage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ModelAndView("redirect:/hello");
        }

        return new ModelAndView("home"); // Trả về home.jsp
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView adminModelAndView = new ModelAndView("admin");
        adminModelAndView.addObject("admin", "admin");
        List<UserEntity> users = service.getAllUsers();
        adminModelAndView.addObject("users", users);
        return adminModelAndView; // Trả về admin.jsp
    }

    @RequestMapping("/admin/add")
    public ModelAndView addUser() {
        ModelAndView adminModelAndView = new ModelAndView("addUser");
        return adminModelAndView; // Trả về admin.jsp
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView userModelAndView = new ModelAndView("user");
        userModelAndView.addObject("user", "user");
        return userModelAndView; // Trả về user.jsp
    }
    
    @RequestMapping("/hello")
    public ModelAndView hello(Authentication authentication) {
        ModelAndView helloModelAndView = new ModelAndView("hello");
        helloModelAndView.addObject("username", authentication.getName());
        if (isAdmin()) {
            //helloModelAndView.addObject("role", "admin");
        } else {
            //helloModelAndView.addObject("role", "user");
        }
        return helloModelAndView; // Trả về hello.jsp
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes) {
        UserEntity user = new UserEntity(username, password);
        user.setEnabled(1);
        try {
            if (service.getUserByUsername(username) != null) {
                redirectAttributes.addFlashAttribute("message", "User already exists");
                return new ModelAndView("redirect:/admin");
            }
            service.addUser(user);
            redirectAttributes.addFlashAttribute("message", "User added successfully");
            return new ModelAndView("redirect:/admin");
        } catch (Exception e) {
            // do nothing
            redirectAttributes.addFlashAttribute("message", "Error adding user: " + e.getLocalizedMessage());
            return new ModelAndView("redirect:/admin");
        }

    }

    @RequestMapping("/test")
    public ModelAndView test() {
        // Trả về test.jsp
        return new ModelAndView("test");
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}

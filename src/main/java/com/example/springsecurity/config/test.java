package com.example.springsecurity.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

    public static void main(String[] args) {
        String password = "admin";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("Encoded Password: " + encodedPassword);
    }
}


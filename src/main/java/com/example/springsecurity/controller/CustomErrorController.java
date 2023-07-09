package com.example.springsecurity.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError() {
        return new ModelAndView("error");
    }
}

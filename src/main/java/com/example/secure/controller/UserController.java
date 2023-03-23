package com.example.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class UserController {
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }
    @GetMapping(value = "/user")
    public String userPage(Model model,Principal principal) {
        model.addAttribute("user", principal);
        return "user";
    }

}


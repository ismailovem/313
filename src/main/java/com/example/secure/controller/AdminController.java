package com.example.secure.controller;


import com.example.secure.entity.User;

import com.example.secure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService serv;
    @Autowired
    public AdminController(UserService serv) {
        this.serv = serv;
    }

    @GetMapping("/")
    public String adminPage() {
        return "index";
    }
    @GetMapping(value = "")
    public String userList(Model model) {
        model.addAttribute("users", serv.getAllUsers());
        return "/admin";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/add";
    }

    @PostMapping("admin/add")
    public String createUser(User user) {
        serv.saveUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", serv.findById(id));
        return "update";
    }


    @PatchMapping(value = "edit/{id}")
    public String updateUser(User updatedUser, @PathVariable("id") Long id) {
        serv.update(updatedUser, id);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        serv.removeById(id);
        return "redirect:/admin";
    }
}
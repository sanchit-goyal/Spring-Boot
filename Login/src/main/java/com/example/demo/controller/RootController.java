package com.example.demo.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal final UserDetails userDetails, Model model) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (authorities.stream().filter(auth -> auth.getAuthority().equals("ADMIN")).count() > 0) {
            // return "Hello Admin"
            model.addAttribute("greeting", "Hello Admin");
        }
        if (authorities.stream().filter(auth -> auth.getAuthority().equals("USER")).count() > 0) {
            // return "Hello User"
            model.addAttribute("greeting", "Hello User");
        } else {
            // return "Default"
            // model.addAttribute("greeting", null)
            // throw some error if required
        }
        return "home";
    }
}

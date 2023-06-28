package com.umaru.evstats.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "/other/login";
    }

    @GetMapping("/login")
    public String login(){
        return "/other/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "/other/signup";
    }

    @GetMapping("/error")
    public String error(){
        return "/other/error";
    }
}

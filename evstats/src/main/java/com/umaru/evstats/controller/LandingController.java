package com.umaru.evstats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    @GetMapping("/home")
    public String home(){
        return "/landing/index";
    }

    @GetMapping("/events")
    public String events(){
        return "/landing/events";
    }

    @GetMapping("/komunitas")
    public String komunitas(){
        return "/landing/komunitas";
    }

    @GetMapping("/bantuan")
    public String bantuan(){
        return "/landing/bantuan";
    }
}

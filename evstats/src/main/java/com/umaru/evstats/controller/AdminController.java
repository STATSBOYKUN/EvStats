package com.umaru.evstats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "/admin/admin_dashboard";
    }

    @GetMapping("/admin/tickets")
    public String tickets(){
        return "/admin/admin_tickets";
    }

    @GetMapping("/admin/users")
    public String users(){
        return "/admin/admin_users";
    }

}

package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.TicketService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private EventService eventsService;

    @Autowired
    private TicketService ticketsService;

    @Autowired
    private UserService usersService;

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "/admin/admin_dashboard";
    }

    @GetMapping("/admin/tickets")
    public String tickets(Model model){
        List<TicketDto> tickets = ticketsService.getTickets();
        model.addAttribute("tickets", tickets);
        return "/admin/admin_tickets";
    }

    @GetMapping("/admin/users")
    public String users(Model model){
        List<UserDto> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "/admin/admin_users";
    }

    @GetMapping("/admin/events")
    public String events(Model model){
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/admin/admin_events";
    }
}

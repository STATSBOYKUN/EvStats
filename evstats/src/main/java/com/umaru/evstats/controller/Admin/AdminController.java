package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.TicketService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("name")
public class AdminController {
    @Autowired
    private EventService eventsService;

    @Autowired
    private TicketService ticketsService;

    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(ModelMap model){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        return "/admin/admin_dashboard";
    }

    @GetMapping("/admin/tickets")
    public String tickets(ModelMap model){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<TicketDto> tickets = ticketsService.getTickets();
        model.addAttribute("tickets", tickets);
        return "/admin/admin_tickets";
    }

    @GetMapping("/admin/users")
    public String users(ModelMap model){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<UserDto> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "/admin/admin_users";
    }

    @GetMapping("/admin/events")
    public String events(ModelMap model){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/admin/admin_events";
    }
}

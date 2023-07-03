package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.HelpDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public String dashboard(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<Integer> earningTicket = new ArrayList<>();

        for (TicketDto ticket : ticketsService.getTickets()) {
            if (ticket.getStatus().equals("Approved")) {
                for (EventDto event : eventsService.getEvents()) {
                    if (event.getName().equals(ticket.getEvent())) {
                        earningTicket.add(event.getPrice() * ticket.getTickets());
                    }
                }
            }
        }

        model.addAttribute("earningTicket", earningTicket);

        List<String> ticketDate = new ArrayList<>();
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd MMMM yyyy");
        for (TicketDto ticket : ticketsService.getTickets()) {
            if (ticket.getStatus().equals("Approved")) {
                ticketDate.add(DateFormat.format(ticket.getDate()));
            }
        }

        model.addAttribute("ticketDate", ticketDate);

        List<Integer> ticketData = new ArrayList<>();
        int ticketApproved = 0;
        int ticketPending = 0;
        int ticketRejected = 0;

        for (TicketDto ticket : ticketsService.getTickets()) {
            switch (ticket.getStatus()) {
                case "Approved" -> ticketApproved = ticketApproved + 1;
                case "Pending" -> ticketPending = ticketPending + 1;
                case "Rejected" -> ticketRejected = ticketRejected + 1;
            }
        }

        ticketData.add(ticketApproved);
        ticketData.add(ticketPending);
        ticketData.add(ticketRejected);

        model.addAttribute("ticketData", ticketData);

        model.addAttribute("jumlahEvent", eventsService.getEvents().size());
        model.addAttribute("jumlahUser", usersService.getUsers().size());
        int jumlahTicket = 0;
        for (TicketDto ticket : ticketsService.getTickets()) {
            if (ticket.getStatus().equals("Approved")) {
                jumlahTicket = jumlahTicket + ticket.getTickets();
            }
        }
        model.addAttribute("jumlahTicket", jumlahTicket);

        double targetUser = usersService.getUsers().size() / 250;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        model.addAttribute("targetUser", decimalFormat.format(targetUser));

        return "/admin/admin_dashboard";
    }

    @GetMapping("/admin/tickets")
    public String tickets(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<TicketDto> tickets = ticketsService.getTickets();
        model.addAttribute("tickets", tickets);
        return "/admin/admin_tickets";
    }

    @GetMapping("/admin/users")
    public String users(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<UserDto> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "/admin/admin_users";
    }

    @GetMapping("/admin/events")
    public String events(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/admin/admin_events";
    }

    @GetMapping("/admin/helps")
    public String helps(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<HelpDto> helps = usersService.getHelps();
        model.addAttribute("helps", helps);
        return "/admin/admin_helps";
    }
}

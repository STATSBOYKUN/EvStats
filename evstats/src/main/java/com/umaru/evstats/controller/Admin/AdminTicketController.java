package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.entity.User;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.TicketService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@SessionAttributes("name")
public class AdminTicketController {
    @Autowired
    private TicketService ticketsService;

    @Autowired
    private EventService eventsService;

    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/admin/tickets/{ticketId}")
    public String viewTicket(ModelMap model, @PathVariable Long ticketId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        TicketDto ticket = ticketsService.getTicket(ticketId);
        model.addAttribute("ticket", ticket);
        return "/admin/tickets/tickets_view";
    }

    @GetMapping("/admin/tickets/{ticketId}/edit")
    public String editTicket(ModelMap model, @PathVariable Long ticketId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        TicketDto ticket = ticketsService.getTicket(ticketId);
        if (ticket == null) {
            return "/admin/admin_tickets";
        }

        model.addAttribute("ticket", ticket);
        return "/admin/tickets/tickets_edit";
    }

    @RequestMapping(value = "/admin/tickets/create/{eventId}", method = RequestMethod.POST)
    public RedirectView storeTicket(ModelMap model, @ModelAttribute("ticket") TicketDto ticketDto, @RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long eventId){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        try {
            String notification = "Tiket Anda sedang diproses, terus pantau notifikasi. Terima kasih.";
            usersService.createNotification(user.getId(), notification);
            EventDto event = eventsService.getEvent(eventId);
            ticketDto.setEvent(event.getName());
            ticketsService.saveTickets(ticketDto, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("ticket", ticketDto);
        return new RedirectView("/events/list");
    }

    @RequestMapping(value = "/admin/tickets/edit", method = RequestMethod.POST)
    public RedirectView editTicket(ModelMap model, @ModelAttribute("ticket") TicketDto ticketDto){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        String notification = "";

        if (ticketDto.getStatus().equals("Pending")) {
            notification = "Tiket Anda sedang diproses, terus pantau notifikasi. Terima kasih.";
        }

        if (ticketDto.getStatus().equals("Approved")) {
            notification = "Tiket Anda telah disetujui, silahkan cek email Anda. Terima kasih.";
        }

        if (ticketDto.getStatus().equals("Rejected")) {
            notification = "Tiket Anda telah ditolak, silahkan cek email Anda. Terima kasih.";
        }

        usersService.createNotification(user.getId(), notification);
        ticketsService.saveTicket(ticketDto);
        model.addAttribute("ticket", ticketDto);

        return new RedirectView("/admin/tickets");
    }

    @RequestMapping(value = "/admin/tickets/{ticketId}/delete", method = RequestMethod.POST)
    public RedirectView deleteTicket(@PathVariable Long ticketId) {
        ticketsService.deleteTicket(ticketId);
        return new RedirectView("/admin/tickets");
    }
}

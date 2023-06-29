package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class AdminTicketController {
    @Autowired
    private TicketService ticketsService;

    @Autowired
    private EventService eventsService;

    @GetMapping("/admin/tickets/{ticketId}")
    public String viewTicket(Model model, @PathVariable Long ticketId) {
        TicketDto ticket = ticketsService.getTicket(ticketId);
        model.addAttribute("ticket", ticket);
        return "/admin/tickets/tickets_view";
    }

    @GetMapping("/admin/tickets/{ticketId}/edit")
    public String editTicket(Model model, @PathVariable Long ticketId) {
        TicketDto ticket = ticketsService.getTicket(ticketId);
        if (ticket == null) {
            return "/admin/admin_tickets";
        }

        model.addAttribute("ticket", ticket);
        return "/admin/tickets/tickets_edit";
    }

    @RequestMapping(value = "/admin/tickets/create/{eventId}", method = RequestMethod.POST)
    public RedirectView storeTicket(Model model, @ModelAttribute("ticket") TicketDto ticketDto, @RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long eventId){
        try {
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
    public RedirectView editTicket(Model model, @ModelAttribute("ticket") TicketDto ticketDto){
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

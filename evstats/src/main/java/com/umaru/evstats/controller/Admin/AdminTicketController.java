package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminTicketController {
    @Autowired
    private TicketService ticketsService;

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

    @RequestMapping(value = "/admin/tickets/create", method = RequestMethod.POST)
    public RedirectView storeTicket(Model model, @ModelAttribute("ticket") TicketDto ticketDto){
        model.addAttribute("ticket", ticketDto);
        ticketsService.saveTickets(ticketDto);
        return new RedirectView("/admin/tickets");
    }

    @RequestMapping(value = "/admin/tickets/{ticketId}/delete", method = RequestMethod.POST)
    public RedirectView deleteTicket(@PathVariable Long ticketId) {
        ticketsService.deleteTicket(ticketId);
        return new RedirectView("/admin/tickets");
    }
}

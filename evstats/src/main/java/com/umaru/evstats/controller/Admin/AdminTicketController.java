package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/invoices/{ticketId}")
    public ResponseEntity<byte[]> viewInvoice(Model model, @PathVariable Long ticketId) {
        byte[] image = ticketsService.getInvoice(ticketId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @RequestMapping(value = "/admin/tickets/create", method = RequestMethod.POST)
    public RedirectView storeTicket(Model model, @ModelAttribute("ticket") TicketDto ticketDto){
        model.addAttribute("ticket", ticketDto);
        ticketsService.saveTickets(ticketDto);
        return new RedirectView("/events/events_list");
    }

    @RequestMapping(value = "/admin/tickets/edit", method = RequestMethod.POST)
    public RedirectView editTicket(Model model, @ModelAttribute("ticket") TicketDto ticketDto){
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

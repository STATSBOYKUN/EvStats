package com.umaru.evstats.controller;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.service.EventService;
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

    @GetMapping("/admin/events")
    public String events(Model model){
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/admin/admin_events";
    }

    @GetMapping("/admin/events/create")
    public String createEvent(Model model){
        model.addAttribute("event", new EventDto());
        return "/admin/events_create";
    }

    @RequestMapping(value = "/admin/events/create", method = RequestMethod.POST)
    public RedirectView storeEvent(Model model, @ModelAttribute("event") EventDto eventDto){
        model.addAttribute("event", eventDto);
        eventsService.saveEvents(eventDto);
        return new RedirectView("/admin/events");
    }

    @RequestMapping(value = "/admin/events/{eventId}/delete", method = RequestMethod.POST)
    public RedirectView deleteEvent(@PathVariable Long eventId) {
        eventsService.deleteEvent(eventId);
        return new RedirectView("/admin/events");
    }
}

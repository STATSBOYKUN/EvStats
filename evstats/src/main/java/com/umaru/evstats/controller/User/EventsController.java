package com.umaru.evstats.controller.User;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EventsController {
    @Autowired
    private EventService eventsService;

    @Autowired
    private UserService usersService;

    @GetMapping("/events/{eventId}")
    public String events(Model model, @PathVariable Long eventId){
        EventDto event = eventsService.getEvent(eventId);
        model.addAttribute("event", event);

        return "/events/events_details";
    }

    @GetMapping("/events/list")
    public String events_list(Model model){
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/events/events_list";
    }

    @GetMapping("/events/tickets/{eventId}")
    public String events_tickets(Model model, @PathVariable Long eventId){
        EventDto event = eventsService.getEvent(eventId);
        model.addAttribute("ticket", new TicketDto());
        model.addAttribute("event", event);
        return "/admin/tickets/tickets_create";
    }

    @GetMapping("/events/favorite")
    public String events_favorite(){
        return "/events/events_favorite";
    }

}

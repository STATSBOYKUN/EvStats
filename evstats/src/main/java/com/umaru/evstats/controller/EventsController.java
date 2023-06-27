package com.umaru.evstats.controller;

import com.umaru.evstats.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController {
    @Autowired
    private EventService eventsService;

    @GetMapping("/events/events_details")
    public String events(){
        return "/events/events_details";
    }

    @GetMapping("/events/events_list")
    public String events_list(){
        return "/events/events_list";
    }

    @GetMapping("/events/events_tickets")
    public String events_tickets(){
        return "/events/events_tickets";
    }

    @GetMapping("/events/events_favorite")
    public String events_favorite(){
        return "/events/events_favorite";
    }

}

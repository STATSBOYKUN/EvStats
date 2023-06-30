package com.umaru.evstats.controller.User;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.UserService;
import com.umaru.evstats.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class EventsController {
    @Autowired
    private EventService eventsService;

    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/events/{eventId}")
    public String events(ModelMap model, @PathVariable Long eventId){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        EventDto event = eventsService.getEvent(eventId);
        model.addAttribute("event", event);

        return "/events/events_details";
    }

    @GetMapping("/events/list")
    public String events_list(ModelMap model){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<EventDto> events = eventsService.getEvents();
        model.addAttribute("events", events);
        return "/events/events_list";
    }

    @GetMapping("/events/tickets/{eventId}")
    public String events_tickets(ModelMap model, @PathVariable Long eventId){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
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

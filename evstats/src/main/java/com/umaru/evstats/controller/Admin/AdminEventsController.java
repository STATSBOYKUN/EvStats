package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminEventsController {
    @Autowired
    private EventService eventsService;

    @GetMapping("/admin/events/create")
    public String createEvent(Model model){
        model.addAttribute("event", new EventDto());
        return "/admin/events/events_create";
    }

    @GetMapping("/admin/events/{eventId}")
    public String viewEvent(Model model, @PathVariable Long eventId) {
        EventDto event = eventsService.getEvent(eventId);
        model.addAttribute("event", event);
        return "/admin/events/events_view";
    }

    @GetMapping("/admin/events/{eventId}/edit")
    public String editEvent(Model model, @PathVariable Long eventId) {
        EventDto event = eventsService.getEvent(eventId);
        if (event == null) {
            return "/admin/admin_events";
        }

        model.addAttribute("event", event);
        return "/admin/events/events_edit";
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

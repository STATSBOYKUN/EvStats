package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.EventService;
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
public class AdminEventsController {
    @Autowired
    private EventService eventsService;

    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/admin/events/create")
    public String createEvent(ModelMap model){
        model.addAttribute("event", new EventDto());
        return "/admin/events/events_create";
    }

    @GetMapping("/admin/events/{eventId}")
    public String viewEvent(ModelMap model, @PathVariable Long eventId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        EventDto event = eventsService.getEvent(eventId);
        model.addAttribute("event", event);
        return "/admin/events/events_view";
    }

    @GetMapping("/admin/events/{eventId}/edit")
    public String editEvent(ModelMap model, @PathVariable Long eventId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        EventDto event = eventsService.getEvent(eventId);
        if (event == null) {
            return "/admin/admin_events";
        }

        model.addAttribute("event", event);
        return "/admin/events/events_edit";
    }

    @RequestMapping(value = "/admin/events/create", method = RequestMethod.POST)
    public RedirectView storeEvent(ModelMap model, @ModelAttribute("event") EventDto eventDto, @RequestParam("imageFile") MultipartFile imageFile){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        model.addAttribute("event", eventDto);
        try {
            eventsService.saveEvents(eventDto, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("/admin/events");
    }

    @RequestMapping(value = "/admin/events/{eventId}/delete", method = RequestMethod.POST)
    public RedirectView deleteEvent(@PathVariable Long eventId) {
        eventsService.deleteEvent(eventId);
        return new RedirectView("/admin/events");
    }


}

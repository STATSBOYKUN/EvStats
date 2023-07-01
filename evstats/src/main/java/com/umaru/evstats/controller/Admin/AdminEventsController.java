package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.FavoriteDto;
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
import java.util.List;

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
        User user= usersService.findUserByEmail(getUserLogin());
        model.addAttribute("event", new EventDto());
        model.put("user", user);
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
        List<FavoriteDto> favoriteDto = usersService.getFavoritedEvent();
        if (favoriteDto != null) {
            for (FavoriteDto favorite : favoriteDto) {
                if (favorite.getEventId() == eventId) {
                    usersService.deleteFavoritedEventByEvent(eventId);
                }
            }
        }
        eventsService.deleteEvent(eventId);
        return new RedirectView("/admin/events");
    }

    @RequestMapping(value = "/events/favorite/{eventId}/create", method = RequestMethod.POST)
    public RedirectView favoriteEvent(@PathVariable Long eventId) {
        User user= usersService.findUserByEmail(getUserLogin());

        usersService.favoriteEvent(user.getId(), eventId);
        return new RedirectView("/events/" + eventId.toString());
    }

    @RequestMapping(value = "/events/favorite/{eventId}/delete", method = RequestMethod.POST)
    public RedirectView deleteFavoritedEvent(@PathVariable Long eventId) {
        User user= usersService.findUserByEmail(getUserLogin());
        usersService.deleteFavoritedEvent(user.getId(), eventId);
        return new RedirectView("/events/" + eventId.toString());
    }

}
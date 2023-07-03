package com.umaru.evstats.controller.user;

import com.umaru.evstats.dto.HelpDto;
import com.umaru.evstats.dto.NotificationDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class LandingController {
    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/home")
    public String home(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<NotificationDto> notificationDtos = usersService.getNotificationsByUser(user.getId());
        model.addAttribute("notifications", notificationDtos);
        return "/landing/index";
    }

    @GetMapping("/events")
    public String events(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        return "/landing/events";
    }

    @GetMapping("/komunitas")
    public String komunitas(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        return "/landing/komunitas";
    }

    @GetMapping("/bantuan")
    public String bantuan(ModelMap model) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        model.addAttribute("help", new HelpDto());
        return "/landing/bantuan";
    }
}

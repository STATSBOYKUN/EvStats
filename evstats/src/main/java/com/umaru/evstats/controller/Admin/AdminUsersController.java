package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.EventService;
import com.umaru.evstats.service.TicketService;
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
public class AdminUsersController {
    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/admin/users/{userId}")
    public String viewUser(ModelMap model, @PathVariable Long userId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        UserDto userDto = usersService.getUser(userId);
        model.addAttribute("user", user);
        return "/admin/users/users_view";
    }

    @GetMapping("/admin/users/{userId}/edit")
    public String editUser(ModelMap model, @PathVariable Long userId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        UserDto userDto = usersService.getUser(userId);
        if (user == null) {
            return "/admin/admin_users";
        }

        model.addAttribute("user", user);
        return "/admin/users/users_edit";
    }

    @RequestMapping(value = "/admin/users/{userId}/delete", method = RequestMethod.POST)
    public RedirectView deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return new RedirectView("/admin/users");
    }
}

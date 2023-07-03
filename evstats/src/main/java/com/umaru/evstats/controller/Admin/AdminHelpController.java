package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.HelpDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminHelpController {
    @Autowired
    private UserService usersService;

    private String getUserLogin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "/admin/help/create", method = RequestMethod.POST)
    public RedirectView createHelp(ModelMap model, @ModelAttribute("help") HelpDto helpDto) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        usersService.saveHelp(helpDto);
        return new RedirectView("/bantuan");
    }

    @GetMapping("/admin/helps/{helpId}")
    public String viewHelp(ModelMap model, @PathVariable Long helpId) {
        User user = usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        HelpDto help = usersService.getHelp(helpId);
        model.addAttribute("help", help);
        return "/admin/helps/helps_view";
    }

    @RequestMapping(value = "/admin/helps/{helpId}/delete", method = RequestMethod.POST)
    public RedirectView deleteHelp(@PathVariable Long helpId) {
        usersService.deleteHelp(helpId);
        return new RedirectView("/admin/helps");
    }
}

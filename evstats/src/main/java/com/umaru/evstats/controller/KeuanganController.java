package com.umaru.evstats.controller;

import com.umaru.evstats.dto.KeuanganDto;
import com.umaru.evstats.model.User;
import com.umaru.evstats.service.KeuanganService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@SessionAttributes("name")
public class KeuanganController {
    @Autowired
    private UserService userService;

    @Autowired
    private KeuanganService keuanganService;

    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/staff/tabelkeuangan")
    public String viewTabelKeuangan(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        List<KeuanganDto> keuangans = keuanganService.getKeuangans();
        model.put("user", user);
        model.addAttribute("keuangans", keuangans);
        model.addAttribute("totalKeuangan", keuanganService.getTotal());
        return "tabelkeuangan";
    }

    @GetMapping("/staff/tabelkeuangan/keuangan")
    public String inputKeuangan(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.put("user", user);
        model.addAttribute("keuangan", new KeuanganDto());
        return "keuangan";
    }

    @PostMapping("/staff/tabelkeuangan")
    public RedirectView storeKeuangan(@ModelAttribute KeuanganDto keuanganDto) {
        keuanganService.saveKeuangan(keuanganDto);
        return new RedirectView("/staff/tabelkeuangan");
    }

}

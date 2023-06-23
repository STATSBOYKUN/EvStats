package com.umaru.evstats.controller;

import com.umaru.evstats.dto.PengembalianDto;
import com.umaru.evstats.model.User;
import com.umaru.evstats.service.KeuanganService;
import com.umaru.evstats.service.PeminjamanService;
import com.umaru.evstats.service.PengembalianService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class PengembalianController {
    @Autowired
    private UserService userService;
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private PengembalianService pengembalianService;
    @Autowired
    private KeuanganService keuanganService;
//    Mapping for ROLE_USER
    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/user/tabelpengembalian/pengembalian")
    public String viewFormPengembalian(ModelMap model) {
        model.put("user", userService.findUserByEmail(getLogedinUsername()));
        return "pengembalian";
    }

    @GetMapping("/user/tabelpengembalian")
    public String viewTabelPengembalian(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.put("user", user);
        model.put("peminjamans", peminjamanService.getPeminjamans(user.getId()));
        model.put("pengembalians", pengembalianService.getPengembalians(user.getId()));
        return "tabelpengembalian";
    }

//    Mapping For ROLE_ADMIN
    @GetMapping("/staff/tabelpengembalian")
    public String viewTabelPengembalianStaff(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.put("user", user);
        model.put("peminjamans", peminjamanService.getPeminjamans());
        model.put("pengembalians", pengembalianService.getPengembalians());
        model.put("keuangans", keuanganService.getKeuangans());
        return "tabelpengembalianStaff";
    }

    @PostMapping("/staff/tabelpengembalian")
    public RedirectView approve(@RequestParam("pengembalian_id") long pengembalian_id,
                                @RequestParam("tanggal_pengembalian") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tanggal_pengembalian) {
        PengembalianDto pengembalianDto = pengembalianService.getPengembalian(pengembalian_id);
        pengembalianService.kembalikan(pengembalianDto, tanggal_pengembalian);
        return new RedirectView("/staff/tabelpengembalian");
    }

}

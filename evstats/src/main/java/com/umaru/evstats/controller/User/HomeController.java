package com.umaru.evstats.controller.User;

import com.umaru.evstats.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "/other/login";
    }

    @GetMapping("/login")
    public String login(){
        return "/other/user_login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        List<String> pekerjaan = List.of("Mahasiswa Non-STIS", "Mahasiswa STIS", "Dosen", "Lainnya");
        List<String> provinsi = List.of("Aceh", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau", "Jambi", "Bengkulu", "Sumatera Selatan", "Kepulauan Bangka Belitung", "Lampung", "Banten", "Jawa Barat", "DKI Jakarta", "Jawa Tengah", "DI Yogyakarta", "Jawa Timur", "Bali", "Nusa Tenggara Barat", "Nusa Tenggara Timur", "Kalimantan Barat", "Kalimantan Tengah", "Kalimantan Selatan", "Kalimantan Timur", "Kalimantan Utara", "Sulawesi Utara", "Sulawesi Tengah", "Sulawesi Selatan", "Sulawesi Tenggara", "Gorontalo", "Sulawesi Barat", "Maluku", "Maluku Utara", "Papua Barat", "Papua");
        model.addAttribute("pekerjaan", pekerjaan);
        model.addAttribute("provinsi", provinsi);
        model.addAttribute("user", new UserDto());
        return "/other/user_signup";
    }

    @GetMapping("/error")
    public String error(){
        return "/other/error";
    }
}

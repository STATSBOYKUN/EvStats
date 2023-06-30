package com.umaru.evstats.controller.User;

import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class HomeController {
    @Autowired
    private UserService usersService;

    private String getLogedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/login")
    public String index(ModelMap model){
        String username = getLogedInUsername();

        model.put("name", username);
        return "/other/user_login";
    }

    @GetMapping("/signup")
    public String signup(ModelMap model){
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
    @PostMapping("/signup")
    public String registration(ModelMap model,
                               @Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result
    ) {
        User existingUser = usersService.findUserByEmail(userDto.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "other/user_signup";
        }

        usersService.saveUser(userDto);
        return "redirect:/signup?success";
    }
}

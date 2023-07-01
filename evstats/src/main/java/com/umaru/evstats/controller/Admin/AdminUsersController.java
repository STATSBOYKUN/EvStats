package com.umaru.evstats.controller.Admin;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.FavoriteDto;
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
import java.util.List;

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
        model.addAttribute("user", userDto);
        return "/admin/users/users_view";
    }

    @GetMapping("/admin/users/{userId}/edit")
    public String editUser(ModelMap model, @PathVariable Long userId) {
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        List<String> pekerjaan = List.of("Mahasiswa Non-STIS", "Mahasiswa STIS", "Dosen", "Lainnya");
        List<String> provinsi = List.of("Aceh", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau", "Jambi", "Bengkulu", "Sumatera Selatan", "Kepulauan Bangka Belitung", "Lampung", "Banten", "Jawa Barat", "DKI Jakarta", "Jawa Tengah", "DI Yogyakarta", "Jawa Timur", "Bali", "Nusa Tenggara Barat", "Nusa Tenggara Timur", "Kalimantan Barat", "Kalimantan Tengah", "Kalimantan Selatan", "Kalimantan Timur", "Kalimantan Utara", "Sulawesi Utara", "Sulawesi Tengah", "Sulawesi Selatan", "Sulawesi Tenggara", "Gorontalo", "Sulawesi Barat", "Maluku", "Maluku Utara", "Papua Barat", "Papua");
        model.addAttribute("pekerjaan", pekerjaan);
        model.addAttribute("provinsi", provinsi);
        UserDto userDto = usersService.getUser(userId);
        if (user == null) {
            return "/admin/admin_users";
        }

        model.addAttribute("user", user);
        return "/admin/users/users_edit";
    }

    @RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
    public RedirectView editTicket(ModelMap model, @ModelAttribute("user") UserDto userDto){
        User user= usersService.findUserByEmail(getUserLogin());
        model.put("user", user);
        usersService.updateUser(userDto);
        model.addAttribute("user", userDto);

        return new RedirectView("/admin/users");
    }


    @RequestMapping(value = "/admin/users/{userId}/delete", method = RequestMethod.POST)
    public RedirectView deleteUser(@PathVariable Long userId) {
        List<FavoriteDto> favoriteDto = usersService.getFavoritedEvent();
        if (favoriteDto != null) {
            for (FavoriteDto favorite : favoriteDto) {
                if (favorite.getUserId() == userId) {
                    usersService.deleteFavoritedEventByUser(userId);
                }
            }
        }
        usersService.deleteUser(userId);
        return new RedirectView("/admin/users");
    }
}

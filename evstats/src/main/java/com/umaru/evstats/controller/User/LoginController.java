package com.umaru.evstats.controller.User;

import com.umaru.evstats.dto.UserDto;
import com.umaru.evstats.entity.User;
import com.umaru.evstats.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private UserService usersService;

    @PostMapping("/signup")
    public String registration(Model model,
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

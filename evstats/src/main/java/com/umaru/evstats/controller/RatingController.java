package com.umaru.evstats.controller;

import com.umaru.evstats.dto.RatingDto;
import com.umaru.evstats.service.RatingService;
import com.umaru.evstats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("name")
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;
    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/user/rating")
    public String viewRatingPage(ModelMap model) {
        model.put("user", userService.findUserByEmail(getLogedinUsername()));
        return "rating";
    }

    @RequestMapping(value = "/user/rating", method = RequestMethod.POST)
    public RedirectView store(Model model,
                              @RequestParam("rating") int rating,
                              @RequestParam("comment") String comment) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setStar(rating);
        ratingDto.setComment(comment);
        model.addAttribute("rating", ratingDto);
        ratingService.saveRating(ratingDto);
        return new RedirectView("/user/");
    }
}

package com.umaru.evstats.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {

    @ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
    public String error(){
        return "/other/error";
    }
}
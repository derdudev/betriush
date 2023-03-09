package com.sexydari.betriush.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String mainRoute(@CookieValue(name = "btusr", required = false) String userCookie){
        return "main";
    }
}

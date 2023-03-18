package com.sexydari.betriush.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class MainController {
    @GetMapping(value = "/")
    public String mainRoute(@CookieValue(name = "btusr", required = false) String userCookie){
        return "main";
    }
}

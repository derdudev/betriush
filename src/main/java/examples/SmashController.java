package examples;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SmashController {

    @GetMapping(value = "/smash")
    public String smash(@RequestBody Smash body){
        System.out.println(body);
        return "smash2";
    }
}

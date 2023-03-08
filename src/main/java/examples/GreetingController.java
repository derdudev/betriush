package examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting2")
    public String greeting2(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingPost(@RequestBody Greeting body, HttpServletResponse response, @CookieValue(name = "color", required = false) String color, Model model){
        model.addAttribute("name", body.getContent());
        response.addCookie(new Cookie("color", "blue"));
        System.out.println(color);
        return "greeting";
    }
}

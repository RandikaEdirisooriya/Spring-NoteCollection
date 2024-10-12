package lk.ijse.NoteCollector_V2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/welcome")
public class welcomeController {

    @GetMapping
    public String viewWelcomeScreen(Model model) {
        // Correct the key and value in the addAttribute method
        model.addAttribute("message", "Hello, welcome to Note Collector!");
        return "welcome";
    }
}

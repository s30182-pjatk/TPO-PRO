package pl.edu.pja.gden.tpopros30182.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pja.gden.tpopros30182.Auth.UserRegisterDTO;
import pl.edu.pja.gden.tpopros30182.Service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register-form";
    }

    @PostMapping("/register")
    public String register(UserRegisterDTO user) {
        userService.register(user);
        return "redirect:/registration-confirm";
    }

    @GetMapping("/registration-confirm")
    public String confirm() {
        return "register-confirm";
    }
}

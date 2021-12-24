package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosinski.user.UserDto;
import pl.kosinski.user.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final String USER_REGISTER = "/user/register"

    UserService userService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new UserDto());
        return USER_REGISTER;
    }

    @PostMapping("/register")
    public String registerUser (@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return USER_REGISTER;
        } else if (userService.findUserByEmail(user.getEmail())) {
            model.addAttribute("duplicateEmail", "Podany adres email zostal już zarejestrowany. Prosimy o podanie innego adresu.");
            return USER_REGISTER;
        }
        userService.saveUser(user);
        return "/user/registration-confirmation";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginUser(HttpSession session) {
        session.setAttribute("userLoggedIn", true);
        return "/index";
    }

    @PostMapping("/logout")
    public String logOutUser(HttpSession session) {
        session.setAttribute("userLoggedIn", false);
        return "index";
    }

}

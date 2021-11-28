package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosinski.user.UserDto;
import pl.kosinski.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "/user/register";
    }

    @PostMapping("/register")
    public String registerUser (@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/register";
        } else if (userService.findUserByEmail(user.getEmail())) {
            model.addAttribute("duplicateEmail", "Podany adres email zostal już zarejestrowany. Prosimy o podanie innego adresu.");
            return "/user/register";
        }
        userService.saveUser(user);
        return "/user/registration-confirmation";
    }

}

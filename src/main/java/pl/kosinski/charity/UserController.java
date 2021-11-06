package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosinski.user.UserService;

@Controller
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

}

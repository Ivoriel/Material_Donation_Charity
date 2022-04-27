package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosinski.user.UserDto;
import pl.kosinski.user.UserService;
import pl.kosinski.user.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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
        } else if (userService.emailExistsInDb(user.getEmail())) {
            model.addAttribute("duplicateEmail", "Podany adres email zostal już zarejestrowany. Prosimy o podanie innego adresu.");
            return "/user/register";
        }
        userService.saveUser(user);
        return "/user/registration-confirmation";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("username");
        model.addAttribute("password");
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginUser(HttpSession session, HttpServletRequest request, Model model) {
        var email = request.getParameter("email");
        var password = request.getParameter("password");
        if (!userService.emailExistsInDb(email)){
            model.addAttribute("unregisteredEmail", "Dla podanego adresu email nie zostało utworzone konto użytkownika");
            return "/user/login";
        }
        if (userService.login(email, password)) {
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("user", userService.findUserByEmail(email));
        }
        return "redirect:/charity";
    }

    @GetMapping("/logout")
    public String logOutUser(HttpSession session) {
        session.setAttribute("userLoggedIn", false);
        return "redirect:/charity";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "/user/user-list";
    }

    @GetMapping("/profile/{userId}")
    public String userProfile(Model model, @PathVariable(value="userId") long userId) {
        model.addAttribute("user", userService.findUser(userId));
        return "/user/user-profile";
    }

    @PostMapping("/profile/{userId}")
    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result,
                           @PathVariable(value="userId") long userId, Model model) {
        if (result.hasErrors()) {
            return "/user/user-profile";
        }
        if (user.getId() == null || user.getPassword() == null) {
            UserDto dbUser = userService.findUser(userId);
            user.setId(dbUser.getId());
            user.setPassword(dbUser.getPassword());
        }
        userService.saveUser(user);
        return "/user/user-profile";
    }

    @PostMapping("/profile/delete/{userId}")
    public String deleteUser(@PathVariable(value = "userId") long userId) {
        userService.deleteUser(userId);
        return "user/deletion-confirmation";
    }

    @ModelAttribute
    public UserType[] userTypes() {
        return UserType.values();
    }

}

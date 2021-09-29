package pl.kosinski.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/charity")
public class HomeController {


    @RequestMapping("")
    public String homeAction(Model model){
        return "index";
    }
}

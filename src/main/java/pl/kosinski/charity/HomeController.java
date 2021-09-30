package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosinski.institution.InstitutionService;


@Controller
@RequestMapping("/charity")
@AllArgsConstructor
public class HomeController {

    InstitutionService institutionService;

    @RequestMapping("")
    public String homeAction(Model model){
        model.addAttribute("doubleInstitutions", institutionService.getDoubleInstitutions());
        return "index";
    }
}

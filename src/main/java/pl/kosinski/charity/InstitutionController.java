package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosinski.institution.InstitutionDto;
import pl.kosinski.institution.InstitutionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/institution")
@AllArgsConstructor
public class InstitutionController {

    InstitutionService institutionService;

    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new InstitutionDto());
        return "/institution/add";
    }

    @PostMapping("/add")
    public String addInstitution(@Valid @ModelAttribute ("institution") InstitutionDto institution, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "/institution/add";
        } else if (institutionService.IsInstitutionDuplicate(institution.getName())) {
            model.addAttribute("duplicateInstitution", "Fundacja o podanej nazwie została już zarejestrowana.");
            return "/institution/add";
        }
        institutionService.saveInstitution(institution);
        return "institution/add-confirmation";
    }

    @GetMapping("/list")
    public String institutionList(Model model) {
        model. addAttribute("institutions", institutionService.findAllInstitutions());
        return "/institution/institution-list";
    }

}

package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosinski.category.CategoryDto;
import pl.kosinski.category.CategoryService;
import pl.kosinski.donation.DonationDto;
import pl.kosinski.donation.DonationService;
import pl.kosinski.institution.InstitutionDto;
import pl.kosinski.institution.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
@AllArgsConstructor
public class DonationController {

    DonationService donationService;
    CategoryService categoryService;
    InstitutionService institutionService;

    @GetMapping("/add")
    public String addDonation(Model model) {
        model.addAttribute("donation", new DonationDto());
        return "/donation/form";
    }

    @PostMapping("/add")
    public String addDonation(@Valid @ModelAttribute("donation") DonationDto donationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/donation/form";
        }
        donationService.saveDonation(donationDto);
        return "/donation/form-confirmation";
    }

    @ModelAttribute("categories")
    public List<CategoryDto> categories() {
        return categoryService.findAllCategories();
    }

    @ModelAttribute("institutions")
    public List<InstitutionDto> institutions() {
        return institutionService.findAllInstitutions();
    }
}

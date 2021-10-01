package pl.kosinski.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosinski.donation.DonationDto;
import pl.kosinski.donation.DonationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/donations")
@AllArgsConstructor
public class DonationController {

    DonationService donationService;

    @GetMapping("/add")
    public String addDonation(Model model) {
        model.addAttribute("donation", new DonationDto());
        return "/donation/form";
    }

    @PostMapping("/add")
    public String addDonation(@Valid DonationDto donationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/donation/form";
        }
        donationDto = donationService.saveDonation(donationDto);
        return "/donation/confirmation";
    }
}

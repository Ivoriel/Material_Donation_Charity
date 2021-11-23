package pl.kosinski.donation;

import lombok.Data;
import pl.kosinski.category.Category;
import pl.kosinski.institution.Institution;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDto {

    private Long id;
    @NotBlank
    private int quantity;
    @NotBlank
    private List<Category> categories = new ArrayList<>();
    @NotBlank
    private Institution institution;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private LocalDate pickUpDate;
    @NotBlank
    private LocalTime pickUpTime;
    private String pickUpComment;

}

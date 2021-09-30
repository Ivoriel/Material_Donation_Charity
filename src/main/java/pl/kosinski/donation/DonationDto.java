package pl.kosinski.donation;

import lombok.Data;
import pl.kosinski.category.Category;
import pl.kosinski.institution.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDto {

    private Long id;
    private int quantity;
    private List<Category> categories = new ArrayList<>();
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}

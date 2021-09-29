package pl.kosinski.donation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kosinski.category.Category;
import pl.kosinski.institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int quantity;
    @NotNull
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @NotNull
    @ManyToOne
    private Institution institution;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String zipCode;
    @NotNull
    private LocalDate pickUpDate;
    @NotNull
    private LocalTime pickUpTime;
    @NotNull
    private String pickUpComment;

    public void setDonationInfo (int quantity, List<Category> categories, Institution institution) {
        this.quantity = quantity;
        this.categories = categories;
        this.institution = institution;
    }

    public void setDonationAddress (String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public void setPickUpData (LocalDate pickUpDate, LocalTime pickUpTime, String pickUpComment) {
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpComment = pickUpComment;
    }
}

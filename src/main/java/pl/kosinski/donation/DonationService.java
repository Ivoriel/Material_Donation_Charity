package pl.kosinski.donation;

import java.util.List;

public interface DonationService {

    DonationDto saveDonation (DonationDto donationDto);

    DonationDto findDonation (long id);

    List<DonationDto> findAllDonations ();

    void deleteDonation (long id);

    Integer quantityOfDonations();

    Integer quantityOfDonatedBags();
}

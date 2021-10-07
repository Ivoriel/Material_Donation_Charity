package pl.kosinski.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository <Donation, Long> {

    @Query("SELECT COUNT (DISTINCT id) FROM Donation")
    Integer countDistinctDonationsById();

    @Query("SELECT SUM(quantity) FROM Donation")
    Integer sumQuantity();
}

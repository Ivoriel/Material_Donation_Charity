package pl.kosinski.donation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository <Donation, Long> {
}

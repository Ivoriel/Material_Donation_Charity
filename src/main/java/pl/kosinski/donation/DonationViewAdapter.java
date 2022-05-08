package pl.kosinski.donation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationViewAdapter implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public DonationDto saveDonation(DonationDto donationDto) {
        Donation donation = new Donation();
        if (donationDto.getId() != null) {
            donation = donationRepository.findById(donationDto.getId()).get();
            donation.setDonationInfo(donationDto.getQuantity(), donationDto.getCategories(), donationDto.getInstitution());
            donation.setDonationAddress(donationDto.getStreet(), donationDto.getCity(), donationDto.getZipCode());
            donation.setPickUpData(donationDto.getPickUpDate(), donationDto.getPickUpTime(), donationDto.getPickUpComment());
            donation = donationRepository.save(donation);
        } else {
            donation.setDonationInfo(donationDto.getQuantity(), donationDto.getCategories(), donationDto.getInstitution());
            donation.setDonationAddress(donationDto.getStreet(), donationDto.getCity(), donationDto.getZipCode());
            donation.setPickUpData(donationDto.getPickUpDate(), donationDto.getPickUpTime(), donationDto.getPickUpComment());
            donation = donationRepository.save(donation);
        }
        return mapEntityToDto(donation);
    }

    @Override
    public DonationDto findDonation(long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        return donation.map(this::mapEntityToDto).orElse(new DonationDto());
    }

    @Override
    public List<DonationDto> findAllDonations() {
        List<Donation> donationList = donationRepository.findAll();
        List<DonationDto> donationDtoList = new ArrayList<>();
        for (Donation donation : donationList) {
            donationDtoList.add(mapEntityToDto(donation));
        }
        return donationDtoList;
    }

    @Override
    public void deleteDonation(long id) {
        donationRepository.deleteById(id);
    }

    @Override
    public Integer quantityOfDonations() {
        return donationRepository.countDistinctDonationsById();
    }

    @Override
    public Integer quantityOfDonatedBags() {
        return donationRepository.sumQuantity();
    }

    private DonationDto mapEntityToDto(Donation donation) {
        DonationDto donationDto = new DonationDto();
        donationDto.setId(donation.getId());
        donationDto.setQuantity(donation.getQuantity());
        donationDto.setCategories(donation.getCategories());
        donationDto.setInstitution(donation.getInstitution());
        donationDto.setStreet(donation.getStreet());
        donationDto.setCity(donation.getCity());
        donationDto.setZipCode(donation.getZipCode());
        donationDto.setPickUpDate(donation.getPickUpDate());
        donationDto.setPickUpTime(donation.getPickUpTime());
        donationDto.setPickUpComment(donation.getPickUpComment());
        return donationDto;
    }

}

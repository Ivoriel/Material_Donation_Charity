package pl.kosinski.donation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kosinski.category.Category;
import pl.kosinski.category.CategoryDto;
import pl.kosinski.institution.Institution;
import pl.kosinski.institution.InstitutionDto;
import pl.kosinski.institution.InstitutionRepository;
import pl.kosinski.institution.InstitutionViewAdapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DonationViewAdapterTest {

    @Autowired
    DonationRepository donationRepository;
    @Autowired
    InstitutionRepository institutionRepository;

    @Test
    public void saveDonation() {
        DonationViewAdapter adapter = new DonationViewAdapter(donationRepository);
        DonationDto donation = createDonationDto(1);
        adapter.saveDonation(donation);
        donation.setId(1L);
        assertEquals(donation, adapter.findDonation(1));
    }

    @Test
    public void findAllDonations() {
        DonationViewAdapter adapter = new DonationViewAdapter(donationRepository);
        List<DonationDto> donationList = new ArrayList<>();
        DonationDto donation1 = createDonationDto(1);
        adapter.saveDonation(donation1);
        donation1.setId(1L);
        donationList.add(donation1);
        DonationDto donation2 = createDonationDto(2);
        adapter.saveDonation(donation2);
        donation2.setId(2L);
        donationList.add(donation2);
        assertEquals(donationList, adapter.findAllDonations());
    }

    @Test
    public void deleteDonation() {
        DonationViewAdapter adapter = new DonationViewAdapter(donationRepository);
        DonationDto donation1 = createDonationDto(1);
        adapter.saveDonation(donation1);
        donation1.setId(1L);
        DonationDto donation2 = createDonationDto(2);
        adapter.saveDonation(donation2);
        donation2.setId(2L);
        assertEquals(donation1, adapter.findDonation(1));
        adapter.deleteDonation(1);
        assertEquals(donation2, adapter.findDonation(2));
        assertThrows(NoSuchElementException.class, () -> adapter.findDonation(1));
    }

    private DonationDto createDonationDto(long id) {
        InstitutionViewAdapter institutionAdapter = new InstitutionViewAdapter(institutionRepository);
        DonationDto donation = new DonationDto();
        donation.setQuantity(Integer.parseInt(Long.toString(id)));
        Category category1 = new Category();
        category1.setName("test_category1");
        Category category2 = new Category();
        category2.setName("test_category2");
        donation.setCategories(Arrays.asList(category1, category2));
        InstitutionDto institution = new InstitutionDto();
        institution.setName("institution_name");
        institution.setDescription("institution_description");
        institution = institutionAdapter.saveInstitution(institution);
        donation.setInstitution(institutionRepository.findById(institution.getId()).get());
        donation.setStreet("test_street");
        donation.setCity("test_city");
        donation.setZipCode("test_ZIP");
        donation.setPickUpDate(LocalDate.now());
        donation.setPickUpTime(LocalTime.now());
        donation.setPickUpComment("test_comment");
        return donation;
    }
}
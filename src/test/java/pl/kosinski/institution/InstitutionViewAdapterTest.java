package pl.kosinski.institution;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InstitutionViewAdapterTest {

    @Autowired
    InstitutionRepository institutionRepository;

    @Test
    public void saveInstitution() {
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        InstitutionDto institution = new InstitutionDto();
        institution.setName("test_name");
        institution.setDescription("test_description");
        adapter.saveInstitution(institution);
        institution.setId(1L);
        assertEquals(institution, adapter.findInstitution(1));
    }

    @Test
    public void findAll() {
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        List<InstitutionDto> institutionList = new ArrayList<>();
        InstitutionDto institution1 = new InstitutionDto();
        institution1.setName("test_name");
        institution1.setDescription("test_description");
        adapter.saveInstitution(institution1);
        institution1.setId(1L);
        institutionList.add(institution1);
        InstitutionDto institution2 = new InstitutionDto();
        institution2.setName("test_name");
        institution2.setDescription("test_description");
        adapter.saveInstitution(institution2);
        institution2.setId(2L);
        institutionList.add(institution2);
        assertEquals(institutionList, adapter.findAll());
    }

    @Test
    public void deleteInstitution() {
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        InstitutionDto institution1 = new InstitutionDto();
        institution1.setName("test_name");
        institution1.setDescription("test_description");
        adapter.saveInstitution(institution1);
        institution1.setId(1L);
        InstitutionDto institution2 = new InstitutionDto();
        institution2.setName("test_name");
        institution2.setDescription("test_description");
        adapter.saveInstitution(institution2);
        institution2.setId(2L);
        assertEquals(institution1, adapter.findInstitution(1));
        adapter.deleteInstitution(1);
        assertEquals(institution2, adapter.findInstitution(2));
        assertThrows(NoSuchElementException.class, () -> adapter.findInstitution(1));

    }


}
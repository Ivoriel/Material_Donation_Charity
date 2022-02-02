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
    public void checkIfInstitutionIsSavedAndRetrievedWhenCalled() {
        // Tests both save and read methods
        // given Category not present in db
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        InstitutionDto institution = generateInstitution();
        // when Institution is saved to db
        adapter.saveInstitution(institution);
        institution.setId(1L);
        // then Institution should be retrievable when called by id
        assertEquals(institution, adapter.findInstitution(1));
    }

    @Test
    public void checkIfListOfInstitutionsPresentInDbCanBeRetrieved() {
        // given Institutions are present in db
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        List<InstitutionDto> institutionList = new ArrayList<>();
        InstitutionDto institution1 = generateInstitution();
        adapter.saveInstitution(institution1);
        institution1.setId(1L);
        institutionList.add(institution1);
        InstitutionDto institution2 = generateInstitution();
        adapter.saveInstitution(institution2);
        institution2.setId(2L);
        institutionList.add(institution2);
        // when call to find all Institutions is executed
        // then all Institutions should be retrieved
        assertEquals(institutionList, adapter.findAllInstitutions());
    }

    @Test
    public void checkIfInstitutionPresentInDbIsDeleted() {
        // given Institution is present in db
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        InstitutionDto institution1 = generateInstitution();
        adapter.saveInstitution(institution1);
        institution1.setId(1L);
        InstitutionDto institution2 = generateInstitution();
        adapter.saveInstitution(institution2);
        institution2.setId(2L);
        assertEquals(institution1, adapter.findInstitution(1));
        // when Institution is deleted from db
        adapter.deleteInstitution(1);
        // then Institution should not be retrievable
        assertEquals(institution2, adapter.findInstitution(2));
        assertThrows(NoSuchElementException.class, () -> adapter.findInstitution(1));

    }

    @Test
    public void checkIfInstitutionIsRetrievedWhenCalledByName() {
        //given Institution is present in db
        InstitutionViewAdapter adapter = new InstitutionViewAdapter(institutionRepository);
        InstitutionDto institution = generateInstitution();
        adapter.saveInstitution(institution);
        institution.setId(1L);
        // when call to find Institution by name is executed
        // then Institution of specific name should be retrieved
        assertEquals(institution, adapter.findInstitutionByName(institution.getName()));
    }

    private InstitutionDto generateInstitution() {
        InstitutionDto institution = new InstitutionDto();
        institution.setName("test_name");
        institution.setDescription("test_description");
        return institution;
    }


}
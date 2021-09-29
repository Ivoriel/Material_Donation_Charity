package pl.kosinski.institution;

import java.util.List;

public interface InstitutionService {

    InstitutionDto saveInstitution(InstitutionDto institutionDto);

    InstitutionDto readInstitution(long id);

    void deleteInstitution(long id);

    List<InstitutionDto> findAll();

}
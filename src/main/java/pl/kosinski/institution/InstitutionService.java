package pl.kosinski.institution;

import java.util.List;

public interface InstitutionService {

    InstitutionDto saveInstitution(InstitutionDto institutionDto);

    InstitutionDto findInstitution(long id);

    InstitutionDto findInstitutionByName(String name);

    void deleteInstitution(long id);

    List<InstitutionDto> findAllInstitutions();

    List<InstitutionDtoDouble> getDoubleInstitutions();

}

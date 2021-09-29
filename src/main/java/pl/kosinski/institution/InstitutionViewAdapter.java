package pl.kosinski.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InstitutionViewAdapter implements InstitutionService {

    InstitutionRepository institutionRepository;

    @Override
    public InstitutionDto saveInstitution(InstitutionDto institutionDto) {
        Institution institution = new Institution();
        if (institutionDto.getId() != null) {
            institution = institutionRepository.findById(institutionDto.getId()).get();
            institution.setNameAndDescription(institutionDto.getName(), institutionDto.getDescription());
            institution = institutionRepository.save(institution);
        } else {
            institution.setNameAndDescription(institutionDto.getName(), institutionDto.getDescription());
            institution = institutionRepository.save(institution);
        }
        return mapEntityToDto(institution);
    }

    @Override
    public InstitutionDto findInstitution(long id) {
        return mapEntityToDto(institutionRepository.findById(id).get());
    }

    @Override
    public List<InstitutionDto> findAll() {
        List<Institution> institutionList = institutionRepository.findAll();
        List<InstitutionDto> institutionDtoList = new ArrayList<>();
        for (Institution institution : institutionList) {
            institutionDtoList.add(mapEntityToDto(institution));
        }
        return institutionDtoList;
    }

    @Override
    public void deleteInstitution(long id) {
        institutionRepository.deleteById(id);
    }

    private InstitutionDto mapEntityToDto(Institution institution) {
        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setId(institution.getId());
        institutionDto.setName(institution.getName());
        institutionDto.setDescription(institution.getDescription());
        return institutionDto;
    }
}

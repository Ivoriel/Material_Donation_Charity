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

    @Override
    public List<InstitutionDtoDouble> getDoubleInstitutions() {
        List<InstitutionDtoDouble> doublesList = new ArrayList<>();
        List<Institution> institutionList = institutionRepository.findAll();
        for (int i = 0; i < institutionList.size(); i = i + 2) {
            InstitutionDtoDouble dtoDouble = new InstitutionDtoDouble();
            dtoDouble.setName1(institutionList.get(i).getName());
            dtoDouble.setDescription1(institutionList.get(i).getDescription());
            if (i+1 != institutionList.size()) {
                dtoDouble.setName2(institutionList.get(i + 1).getName());
                dtoDouble.setDescription2(institutionList.get(i + 1).getDescription());
            }
            doublesList.add(dtoDouble);
        }
        return doublesList;
    }

    private InstitutionDto mapEntityToDto(Institution institution) {
        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setId(institution.getId());
        institutionDto.setName(institution.getName());
        institutionDto.setDescription(institution.getDescription());
        return institutionDto;
    }
}

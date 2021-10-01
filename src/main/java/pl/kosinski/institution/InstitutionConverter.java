package pl.kosinski.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class InstitutionConverter implements Converter<String, InstitutionDto> {

    @Autowired
    private InstitutionService institutionService;

    public InstitutionDto convert (String source) {
        return institutionService.findInstitution(Long.parseLong(source));
    }
}

package pl.kosinski.institution;

import lombok.Data;

/*
This Dto is tailored specifically for display of institutions as a list on the landing page.
Institutions are displayed in two columns. By utilizing this Dto less frontend code is necessary.
 */
@Data
public class InstitutionDtoDouble {

    private String name1;
    private String description1;
    private String name2;
    private String description2;

}

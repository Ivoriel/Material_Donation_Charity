package pl.kosinski.institution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionRepository extends JpaRepository <Institution, Long> {

    Institution findByName(String name);

}

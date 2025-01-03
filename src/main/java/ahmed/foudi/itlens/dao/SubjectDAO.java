package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Long> {
}

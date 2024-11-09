package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDAO extends JpaRepository<Survey, Long> {
}

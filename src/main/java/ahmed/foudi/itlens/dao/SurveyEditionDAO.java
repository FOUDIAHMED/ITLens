package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Survey;
import ahmed.foudi.itlens.entities.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyEditionDAO extends JpaRepository<SurveyEdition, Long> {
}

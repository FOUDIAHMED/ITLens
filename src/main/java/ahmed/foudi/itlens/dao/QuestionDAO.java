package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
}

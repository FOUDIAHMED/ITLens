package ahmed.foudi.itlens.dao;

import ahmed.foudi.itlens.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDAO extends JpaRepository<Answer, Long> {
}

package ahmed.foudi.itlens.dto.questiondto;

import ahmed.foudi.itlens.entities.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequestDto {
     String question;
     QuestionType questionType;
     Long subjectId;
}

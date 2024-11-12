package ahmed.foudi.itlens.dto.answerdto;

import ahmed.foudi.itlens.dto.questiondto.QuestionEmbeddedDTO;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import lombok.Data;

@Data
public class AnswerResponseDto {
    Long id;
    String answer;
    int selectionCount;
    QuestionEmbeddedDTO question;
}

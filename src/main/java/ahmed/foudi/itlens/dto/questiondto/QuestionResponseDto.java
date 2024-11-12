package ahmed.foudi.itlens.dto.questiondto;

import ahmed.foudi.itlens.dto.answerdto.AnswerEmbddedDto;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.dto.subjectdto.SubjectEmbdedDto;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDto {
     Long id;
     String question;
     int answerCount;
     SubjectEmbdedDto subject;
     List<AnswerResponseDto> answers;


}

package ahmed.foudi.itlens.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDto {
    private Long questionId;
    private Long answerId;
    private List<AnswerDto> answers;
}

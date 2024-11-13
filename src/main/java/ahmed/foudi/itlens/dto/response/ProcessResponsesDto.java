package ahmed.foudi.itlens.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProcessResponsesDto {
    List<QuestionResponseDto> answers;
}

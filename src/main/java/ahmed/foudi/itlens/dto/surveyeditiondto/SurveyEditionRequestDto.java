package ahmed.foudi.itlens.dto.surveyeditiondto;

import ahmed.foudi.itlens.entities.Survey;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SurveyEditionRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private int year;
    private Long surveyId;
}

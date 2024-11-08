package ahmed.foudi.itlens.dto.surveyeditiondto;

import ahmed.foudi.itlens.dto.surveydto.SurveyResponseDto;
import ahmed.foudi.itlens.entities.Subject;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SurveyEditionResponseDto {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int year;

    private SurveyResponseDto survey;

    private List<Subject> subjects;
}

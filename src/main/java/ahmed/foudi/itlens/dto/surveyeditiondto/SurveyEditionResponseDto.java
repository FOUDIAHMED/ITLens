package ahmed.foudi.itlens.dto.surveyeditiondto;

import ahmed.foudi.itlens.dto.subjectdto.SubjectResponseDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyEmbdedDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SurveyEditionResponseDto {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int year;

    private SurveyEmbdedDto survey;

    private List<SubjectResponseDto> subjects;
}

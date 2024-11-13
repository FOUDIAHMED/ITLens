package ahmed.foudi.itlens.dto.surveyeditiondto;

import ahmed.foudi.itlens.dto.surveydto.SurveyEmbdedDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurveyEditionEmbeddedDTO {
    Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int year;
}

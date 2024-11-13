package ahmed.foudi.itlens.dto.surveydto;

import ahmed.foudi.itlens.dto.ownerdto.OwnerEmbdedDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionEmbeddedDTO;
import ahmed.foudi.itlens.entities.SurveyEdition;
import lombok.Data;

import java.util.List;

@Data
public class SurveyResponseDto {
    private Long id;
    private String title;
    private String description;
    private OwnerEmbdedDto owner;
    private List<SurveyEditionEmbeddedDTO> surveyEditions;
}

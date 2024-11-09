package ahmed.foudi.itlens.dto.surveydto;

import ahmed.foudi.itlens.dto.ownerdto.OwnerEmbdedDto;
import lombok.Data;

@Data
public class SurveyRequestDto {
    private String title;
    private String description;
    private Long ownerId;
}

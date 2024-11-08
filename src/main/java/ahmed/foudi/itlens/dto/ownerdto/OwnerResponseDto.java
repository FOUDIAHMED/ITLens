package ahmed.foudi.itlens.dto.ownerdto;

import ahmed.foudi.itlens.dto.surveydto.SurveyEmbdedDto;
import lombok.Data;

import java.util.List;

@Data
public class OwnerResponseDto {
    private Long id;
    private String name;

    private List<SurveyEmbdedDto> surveys;

}

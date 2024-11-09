package ahmed.foudi.itlens.mappers;

import ahmed.foudi.itlens.dto.surveydto.SurveyEmbdedDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyResponseDto;
import ahmed.foudi.itlens.entities.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurveyRequestDtoMapper {

    @Mapping(source = "ownerId" , target = "owner.id")
    Survey toEntity(SurveyRequestDto dto);

    SurveyResponseDto toResponseDto(Survey entity);

}

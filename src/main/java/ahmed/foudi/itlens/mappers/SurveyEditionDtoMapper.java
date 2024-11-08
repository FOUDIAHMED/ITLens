package ahmed.foudi.itlens.mappers;

import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionRequestDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionResponseDto;
import ahmed.foudi.itlens.entities.SurveyEdition;
import ch.qos.logback.core.model.INamedModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurveyEditionDtoMapper {
    @Mapping(source = "surveyId" , target = "survey.id")
    SurveyEdition toEntity(SurveyEditionRequestDto dto);

    SurveyRequestDto toDto(SurveyEdition entity);
}
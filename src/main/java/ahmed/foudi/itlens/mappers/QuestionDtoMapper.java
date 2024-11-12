package ahmed.foudi.itlens.mappers;

import ahmed.foudi.itlens.dto.questiondto.QuestionRequestDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyResponseDto;
import ahmed.foudi.itlens.entities.Question;
import ahmed.foudi.itlens.entities.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {

    @Mapping(source = "subjectId" , target = "subject.id")
    Question toEntity(QuestionRequestDto dto);

    QuestionResponseDto toResponseDto(Question entity);

}

package ahmed.foudi.itlens.mappers;

import ahmed.foudi.itlens.dto.answerdto.AnswerRequestDto;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionRequestDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.entities.Answer;
import ahmed.foudi.itlens.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {
    @Mapping(source = "questionId" , target = "question.id")
    Answer toEntity(AnswerRequestDto dto);

    AnswerResponseDto toResponseDto(Answer entity);
}

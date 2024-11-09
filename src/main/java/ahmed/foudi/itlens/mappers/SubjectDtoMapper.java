package ahmed.foudi.itlens.mappers;

import ahmed.foudi.itlens.dto.subjectdto.SubjectRequestDto;
import ahmed.foudi.itlens.dto.subjectdto.SubjectResponseDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionRequestDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionResponseDto;
import ahmed.foudi.itlens.entities.Subject;
import ahmed.foudi.itlens.entities.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectDtoMapper {
    @Mapping(source = "parentSubjectId" , target = "parentSubject.id")
    @Mapping(source = "surveyEditionId" , target = "surveyEdition.id")
    Subject toEntity(SubjectRequestDto dto);

    SubjectResponseDto toDto(Subject entity);
}

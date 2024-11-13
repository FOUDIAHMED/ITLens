package ahmed.foudi.itlens.dto.subjectdto;

import ahmed.foudi.itlens.dto.questiondto.QuestionEmbeddedDTO;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionEmbeddedDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubjectResponseDto {
    private Long id;
    private String title;
    private SubjectEmbdedDto parentSubject; // Basic details of the parent subject
    private SurveyEditionEmbeddedDTO surveyEdition; // Basic details of the survey edition
    private List<SubjectEmbdedDto> subSubjects; // List of sub-subjects in embedded format
    private List<QuestionResponseDto> questions;

}

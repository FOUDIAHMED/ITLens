package ahmed.foudi.itlens.dto.subjectdto;

import lombok.Data;

@Data
public class SubjectRequestDto {
    private String title;
    private Long parentSubjectId;
    private Long surveyEditionId;
}

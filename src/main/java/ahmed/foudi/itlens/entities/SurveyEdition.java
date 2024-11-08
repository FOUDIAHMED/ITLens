package ahmed.foudi.itlens.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy="surveyedition", fetch = FetchType.EAGER)
    private List<Subject> subjects;

}

package ahmed.foudi.itlens.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "parentSubject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Subject> subSubjects;

    // Self-referencing many-to-one relationship
    @ManyToOne
    @JoinColumn(name = "parent_subject_id")
    private Subject parentSubject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surveyedition_id", nullable = false)
    private SurveyEdition surveyEdition;
}

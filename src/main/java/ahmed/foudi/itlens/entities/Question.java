package ahmed.foudi.itlens.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private int answerCount;

    @OneToMany(mappedBy="answer", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @ManyToOne
    private Subject subject;


}

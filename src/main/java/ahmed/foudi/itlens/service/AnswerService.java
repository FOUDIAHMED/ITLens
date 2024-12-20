package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.AnswerDAO;
import ahmed.foudi.itlens.dao.QuestionDAO;
import ahmed.foudi.itlens.dao.SurveyDAO;
import ahmed.foudi.itlens.dto.answerdto.AnswerRequestDto;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.dto.response.AnswerDto;
import ahmed.foudi.itlens.dto.response.ProcessResponsesDto;
import ahmed.foudi.itlens.dto.response.QuestionResponseDto;
import ahmed.foudi.itlens.entities.Answer;
import ahmed.foudi.itlens.entities.Question;
import ahmed.foudi.itlens.entities.Survey;
import ahmed.foudi.itlens.mappers.AnswerDtoMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService implements ServiceInterface<Long, AnswerRequestDto, AnswerResponseDto> {
    private final AnswerDAO answerDAO;
    private final AnswerDtoMapper answerDtoMapper;
    private final QuestionDAO questionDAO;
    private final SurveyDAO surveyDAO;
    @Override
    public List<AnswerResponseDto> findAll() {
        List<Answer> answers=answerDAO.findAll();
        return answers.stream().map(answerDtoMapper::toResponseDto).toList();

    }

    @Override
    public AnswerResponseDto findById(Long id) {
        Answer answer = answerDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("asnwer with id " + id + " not found"));
        return answerDtoMapper.toResponseDto(answer);

    }

    @Override
    public AnswerResponseDto create(AnswerRequestDto dto) {
        Answer answer = answerDtoMapper.toEntity(dto);
        answerDAO.save(answer);
        Question question=questionDAO.findById(answer.getQuestion().getId()).orElseThrow(() -> new EntityNotFoundException("question with id " + answer.getQuestion().getId() + " not found"));
        answer.setQuestion(question);
        return answerDtoMapper.toResponseDto(answer);
    }

    @Override
    public AnswerResponseDto update(Long id, AnswerRequestDto dto) {
        Answer answer = answerDAO.findById(id).orElseThrow(EntityNotFoundException::new);
        answer.setAnswer(dto.getAnswer());
        answerDAO.save(answer);
        return answerDtoMapper.toResponseDto(answer);

    }

    @Override
    public void delete(Long id) {
        if(answerDAO.findById(id).isPresent()){
            answerDAO.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("this entity with this id is not found");

    }

    public void processResponses(Long surveyId, ProcessResponsesDto dto) {
        Survey survey = surveyDAO.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey with id " + surveyId + " not found"));

        for (QuestionResponseDto questionResponse : dto.getAnswers()) {
            Long questionId = questionResponse.getQuestionId();

            Question question = questionDAO.findById(questionId)
                    .orElseThrow(() -> new EntityNotFoundException("Question with id " + questionId + " not found"));

            boolean isQuestionInSurvey = survey.getSurveyEditions().stream()
                    .flatMap(edition -> edition.getSubjects().stream())
                    .flatMap(subject -> subject.getQuestions().stream())
                    .anyMatch(q -> q.getId().equals(questionId));

            if (!isQuestionInSurvey) {
                throw new IllegalArgumentException("Question with id " + questionId + " does not belong to the survey with id " + surveyId);
            }

            if (questionResponse.getAnswerId() != null) {
                Long answerId = questionResponse.getAnswerId();
                Answer answer = answerDAO.findById(answerId)
                        .orElseThrow(() -> new EntityNotFoundException("Answer with id " + answerId + " not found"));

                answer.setSelectionCount(answer.getSelectionCount() + 1);
                answerDAO.save(answer);

                question.setAnswerCount(question.getAnswerCount() + 1);
                questionDAO.save(question);

            } else if (questionResponse.getAnswers() != null && !questionResponse.getAnswers().isEmpty()) {
                for (AnswerDto answerDto : questionResponse.getAnswers()) {
                    Long answerId = answerDto.getAnswerId();
                    Answer answer = answerDAO.findById(answerId)
                            .orElseThrow(() -> new EntityNotFoundException("Answer with id " + answerId + " not found"));

                    answer.setSelectionCount(answer.getSelectionCount() + 1);
                    answerDAO.save(answer);
                }

                question.setAnswerCount(question.getAnswerCount() + questionResponse.getAnswers().size());
                questionDAO.save(question);
            }
        }
    }


}

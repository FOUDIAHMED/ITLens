package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.QuestionDAO;
import ahmed.foudi.itlens.dto.questiondto.QuestionRequestDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.entities.Question;
import ahmed.foudi.itlens.mappers.QuestionDtoMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService implements ServiceInterface<Long, QuestionRequestDto, QuestionResponseDto> {
    QuestionDAO  questionDAO;
    QuestionDtoMapper questionDtoMapper;



    @Override
    public List<QuestionResponseDto> findAll() {
        List<Question> questions = questionDAO.findAll();
        return questions.stream().map(questionDtoMapper::toResponseDto).toList();
    }

    @Override
    public QuestionResponseDto findById(Long id) {
        Question question = questionDAO.findById(id).orElseThrow(EntityNotFoundException::new);
        return questionDtoMapper.toResponseDto(question);
    }

    @Override
    public QuestionResponseDto create(QuestionRequestDto dto) {
        Question question = questionDtoMapper.toEntity(dto);
        questionDAO.save(question);
        return questionDtoMapper.toResponseDto(question);
    }

    @Override
    public QuestionResponseDto update(Long id, QuestionRequestDto dto) {
        Question question = questionDAO.findById(id).orElseThrow(EntityNotFoundException::new);
        question.setQuestion(dto.getQuestion());
        question.setQuestionType(dto.getQuestionType());
        questionDAO.save(question);
        return questionDtoMapper.toResponseDto(question);
    }

    @Override
    public void delete(Long id) {
        if(!questionDAO.existsById(id)) {
            throw new EntityNotFoundException("Question not found with this id "+ id);
        }
        questionDAO.deleteById(id);

    }
}

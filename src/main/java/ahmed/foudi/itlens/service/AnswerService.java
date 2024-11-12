package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.AnswerDAO;
import ahmed.foudi.itlens.dto.answerdto.AnswerRequestDto;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.entities.Answer;
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
    @Override
    public List<AnswerResponseDto> findAll() {
        List<Answer> answers=answerDAO.findAll();
        return answers.stream().map(answerDtoMapper::toResponseDto).toList();

    }

    @Override
    public AnswerResponseDto findById(Long id) {
        Answer answer = answerDAO.findById(id).orElseThrow(EntityNotFoundException::new);
        return answerDtoMapper.toResponseDto(answer);

    }

    @Override
    public AnswerResponseDto create(AnswerRequestDto dto) {
        Answer answer = answerDtoMapper.toEntity(dto);
        answerDAO.save(answer);
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
}

package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.AnswerDAO;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.entities.Answer;
import ahmed.foudi.itlens.mappers.AnswerDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AnswerServiceTest {
    @Mock
    AnswerDAO answerDAO;

    @Mock
    AnswerDtoMapper answerDtoMapper;

    @InjectMocks
    AnswerService answerService;

    private Answer answer;
    private AnswerResponseDto answerResponseDto;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        answer = new Answer();
        answer.setId(1L);
        answer.setAnswer("Sample Answer");

        answerResponseDto = new AnswerResponseDto();
        answerResponseDto.setId(1L);
        answerResponseDto.setAnswer("Sample Answer");
    }

    @Test
    public void testFindAll() {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);

        when(answerDAO.findAll()).thenReturn(answers);
        when(answerDtoMapper.toResponseDto(answer)).thenReturn(answerResponseDto);

        List<AnswerResponseDto> result = answerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sample Answer", result.get(0).getAnswer());
    }

    @Test
    public void testFindById_Found() {
        when(answerDAO.findById(1L)).thenReturn(Optional.of(answer));
        when(answerDtoMapper.toResponseDto(answer)).thenReturn(answerResponseDto);

        AnswerResponseDto result = answerService.findById(1L);

        assertNotNull(result);
        assertEquals("Sample Answer", result.getAnswer());
    }

    @Test
    public void testFindById_NotFound() {
        when(answerDAO.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            answerService.findById(999L);
        });
    }
}
package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.QuestionDAO;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.entities.Question;
import ahmed.foudi.itlens.mappers.QuestionDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class QuestionServiceeTest {
    @Mock
    private QuestionDAO questionDAO;

    @Mock
    private QuestionDtoMapper questionDtoMapper;

    @InjectMocks
    private QuestionService questionService;


    private Question question;
    private QuestionResponseDto questionResponseDto;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        question = new Question();
        question.setId(1L);
        question.setQuestion("Sample Question");
        question.setAnswerCount(3);

        questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(1L);
        questionResponseDto.setQuestion("Sample Question");
        questionResponseDto.setAnswerCount(3);

        when(questionDAO.findById(1L)).thenReturn(Optional.of(question));  // Simulating the database behavior
        when(questionDtoMapper.toResponseDto(question)).thenReturn(questionResponseDto);  // Simulating DTO mapping
    }
    @Test
    public void testFindById() {
        QuestionResponseDto result = questionService.findById(1L);

        assertNotNull(result);
        assertEquals("Sample Question", result.getQuestion());
        assertEquals(3, result.getAnswerCount());
    }

    @Test
    public void testFindByIdNotFound() {
        when(questionDAO.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            questionService.findById(999L);
        });
    }
    @Test
    public void testFindAll() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestion("Sample Question 1");
        question1.setAnswerCount(3);

        Question question2 = new Question();
        question2.setId(2L);
        question2.setQuestion("Sample Question 2");
        question2.setAnswerCount(5);

        QuestionResponseDto dto1 = new QuestionResponseDto();
        dto1.setId(1L);
        dto1.setQuestion("Sample Question 1");
        dto1.setAnswerCount(3);

        QuestionResponseDto dto2 = new QuestionResponseDto();
        dto2.setId(2L);
        dto2.setQuestion("Sample Question 2");
        dto2.setAnswerCount(5);

        when(questionDAO.findAll()).thenReturn(Arrays.asList(question1, question2));
        when(questionDtoMapper.toResponseDto(question1)).thenReturn(dto1);
        when(questionDtoMapper.toResponseDto(question2)).thenReturn(dto2);

        List<QuestionResponseDto> result = questionService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Sample Question 1", result.get(0).getQuestion());
        assertEquals(3, result.get(0).getAnswerCount());
        assertEquals("Sample Question 2", result.get(1).getQuestion());
        assertEquals(5, result.get(1).getAnswerCount());
    }
}
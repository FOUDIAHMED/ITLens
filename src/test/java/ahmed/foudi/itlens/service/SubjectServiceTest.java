package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.SubjectDAO;
import ahmed.foudi.itlens.dto.subjectdto.SubjectRequestDto;
import ahmed.foudi.itlens.dto.subjectdto.SubjectResponseDto;
import ahmed.foudi.itlens.entities.Subject;
import ahmed.foudi.itlens.mappers.SubjectDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class SubjectServiceTest {

    @Mock
    private SubjectDAO subjectDAO;

    @Mock
    private SubjectDtoMapper subjectDtoMapper;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject;
    private SubjectResponseDto subjectResponseDto;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        subject = new Subject();
        subject.setId(1L);
        subject.setTitle("Sample Subject");

        subjectResponseDto = new SubjectResponseDto();
        subjectResponseDto.setId(1L);
        subjectResponseDto.setTitle("Sample Subject");
    }

    @Test
    public void testFindAll() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);

        when(subjectDAO.findAll()).thenReturn(subjects);
        when(subjectDtoMapper.toDto(subject)).thenReturn(subjectResponseDto);

        List<SubjectResponseDto> result = subjectService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sample Subject", result.get(0).getTitle());
    }

    @Test
    public void testFindById_Found() {
        when(subjectDAO.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectDtoMapper.toDto(subject)).thenReturn(subjectResponseDto);

        SubjectResponseDto result = subjectService.findById(1L);

        assertNotNull(result);
        assertEquals("Sample Subject", result.getTitle());
    }



    @Test
    public void testCreate() {
        SubjectRequestDto requestDto = new SubjectRequestDto();
        requestDto.setTitle("New Subject");
        requestDto.setParentSubjectId(null);

        Subject newSubject = new Subject();
        newSubject.setTitle("New Subject");

        when(subjectDtoMapper.toEntity(requestDto)).thenReturn(newSubject);
        when(subjectDAO.save(newSubject)).thenReturn(subject);
        when(subjectDtoMapper.toDto(subject)).thenReturn(subjectResponseDto);

        SubjectResponseDto result = subjectService.create(requestDto);

        assertNotNull(result);
        assertEquals("Sample Subject", result.getTitle());
    }

    @Test
    public void testUpdate() {
        SubjectRequestDto requestDto = new SubjectRequestDto();
        requestDto.setTitle("Updated Subject");

        when(subjectDAO.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectDtoMapper.toDto(subject)).thenReturn(subjectResponseDto);

        SubjectResponseDto result = subjectService.update(1L, requestDto);

        assertNotNull(result);
        assertEquals("Sample Subject", result.getTitle());
    }

    @Test
    public void testDelete_Success() {
        when(subjectDAO.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> subjectService.delete(1L));
    }



    @Test
    public void testDelete_NotFound() {
        when(subjectDAO.existsById(999L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            subjectService.delete(999L);
        });
    }
}
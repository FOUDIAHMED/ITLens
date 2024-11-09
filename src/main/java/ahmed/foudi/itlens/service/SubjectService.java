package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.SubjectDAO;
import ahmed.foudi.itlens.dto.subjectdto.SubjectRequestDto;
import ahmed.foudi.itlens.dto.subjectdto.SubjectResponseDto;
import ahmed.foudi.itlens.entities.Subject;
import ahmed.foudi.itlens.mappers.SubjectDtoMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SubjectService implements ServiceInterface<Long, SubjectRequestDto, SubjectResponseDto> {
    private final SubjectDAO subjectDAO;
    private final SubjectDtoMapper subjectDtoMapper;
    @Override
    public List<SubjectResponseDto> findAll() {
        List<Subject> subjects=subjectDAO.findAll();
        return subjects.stream().map(subjectDtoMapper::toDto).toList();
    }

    @Override
    public SubjectResponseDto findById(Long id) {
        return subjectDtoMapper.toDto(subjectDAO.findById(id).get());
    }

    @Override
    public SubjectResponseDto create(SubjectRequestDto dto) {
        Subject subject = subjectDtoMapper.toEntity(dto);
        if (dto.getParentSubjectId() == null) {
            subject.setParentSubject(null);
        } else {
            Subject parentSubject = subjectDAO.findById(dto.getParentSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent Subject not found"));
            subject.setParentSubject(parentSubject);
            parentSubject.getSubSubjects().add(subject);
        }
        Subject savedSubject = subjectDAO.save(subject);
        return subjectDtoMapper.toDto(savedSubject);
    }


    @Override
    public SubjectResponseDto update(Long id, SubjectRequestDto dto) {
        Subject subject=subjectDAO.findById(id).get();
        subject.setTitle(dto.getTitle());
        return subjectDtoMapper.toDto(subject);
    }

    @Override
    public void delete(Long id) {
        if(!subjectDAO.existsById(id)) {
            throw new EntityNotFoundException("Subject with id " + id + " not found");
        }
        subjectDAO.deleteById(id);

    }
}

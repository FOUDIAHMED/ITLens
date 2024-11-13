package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.SurveyDAO;
import ahmed.foudi.itlens.dto.surveydto.SurveyEmbdedDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyResponseDto;
import ahmed.foudi.itlens.entities.Owner;
import ahmed.foudi.itlens.entities.Survey;
import ahmed.foudi.itlens.mappers.SurveyRequestDtoMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService implements ServiceInterface<Long, SurveyRequestDto, SurveyResponseDto> {
    private  final SurveyDAO surveyDAO;
    private final OwnerService ownerService;
    private final SurveyRequestDtoMapper surveyRequestDtoMapper;
    @Override
    public List<SurveyResponseDto> findAll() {
        List<Survey> surveys = surveyDAO.findAll();
        return surveys.stream().map(surveyRequestDtoMapper::toResponseDto).toList();
    }

    @Override
    public SurveyResponseDto findById(Long id) {
        Survey owner=surveyDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("survey with id " + id + " not found"));
        return surveyRequestDtoMapper.toResponseDto(owner);
    }

    @Override
    public SurveyResponseDto create(SurveyRequestDto dto) {
        Survey savedSurvey = surveyDAO.save(surveyRequestDtoMapper.toEntity(dto));
        Survey surveyWithOwner = surveyDAO.findById(savedSurvey.getId()).orElseThrow(
                () -> new EntityNotFoundException("Survey not found")
        );
        return surveyRequestDtoMapper.toResponseDto(surveyWithOwner);

    }

    @Override
    public SurveyResponseDto update(Long id, SurveyRequestDto dto) {
        Survey savedSurvey = surveyDAO.findById(id).get();
        savedSurvey.setTitle(dto.getTitle());
        savedSurvey.setDescription(dto.getDescription());
        surveyDAO.save(savedSurvey);
        return surveyRequestDtoMapper.toResponseDto(savedSurvey);
    }

    @Override
    public void delete(Long id) {
        if(!surveyDAO.existsById(id)) {
            throw new EntityNotFoundException("Survey with id " + id + " not found");
        }
        surveyDAO.deleteById(id);
    }
}

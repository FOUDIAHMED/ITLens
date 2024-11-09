package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.SurveyDAO;
import ahmed.foudi.itlens.dao.SurveyEditionDAO;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionRequestDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionResponseDto;
import ahmed.foudi.itlens.entities.Survey;
import ahmed.foudi.itlens.entities.SurveyEdition;
import ahmed.foudi.itlens.mappers.SurveyEditionDtoMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyEditionService implements ServiceInterface<Long, SurveyEditionRequestDto, SurveyEditionResponseDto> {

    private final SurveyEditionDAO surveyEditionDAO;
    private final SurveyEditionDtoMapper surveyEditionDtoMapper;

    @Override
    public List<SurveyEditionResponseDto> findAll() {
        List<SurveyEdition> surveyEditions = surveyEditionDAO.findAll();
        return surveyEditions.stream().map(surveyEditionDtoMapper::toDto).toList();
    }

    @Override
    public SurveyEditionResponseDto findById(Long id) {
        SurveyEdition surveyEdition = surveyEditionDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey edition not found"));
        return surveyEditionDtoMapper.toDto(surveyEdition);
    }

    @Override
    public SurveyEditionResponseDto create(SurveyEditionRequestDto dto) {
        SurveyEdition savedSurvey = surveyEditionDAO.save(surveyEditionDtoMapper.toEntity(dto));
        return surveyEditionDtoMapper.toDto(savedSurvey);
    }

    @Override
    public SurveyEditionResponseDto update(Long id, SurveyEditionRequestDto dto) {
        SurveyEdition surveyEdition=surveyEditionDAO.findById(id).get();
        surveyEdition.setStartDate(dto.getStartDate());
        surveyEdition.setEndDate(dto.getEndDate());
        surveyEdition.setYear(dto.getYear());
        surveyEditionDAO.save(surveyEdition);
        return surveyEditionDtoMapper.toDto(surveyEdition);
    }

    @Override
    public void delete(Long aLong) {
        if(!surveyEditionDAO.existsById(aLong)) {
            throw new EntityNotFoundException("Survey edition not found");
        }
        surveyEditionDAO.deleteById(aLong);

    }
}

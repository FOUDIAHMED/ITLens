package ahmed.foudi.itlens.controller;

import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionRequestDto;
import ahmed.foudi.itlens.dto.surveyeditiondto.SurveyEditionResponseDto;
import ahmed.foudi.itlens.service.SurveyEditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("surveys_editions/")
@RequiredArgsConstructor
public class SurveyEditionController {
    private final SurveyEditionService service;

    @GetMapping("all/")
    public ResponseEntity<List<SurveyEditionResponseDto>> findAll() {
        List<SurveyEditionResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDto> findById(@PathVariable Long id){
        SurveyEditionResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<SurveyEditionResponseDto> create(@RequestBody @Valid SurveyEditionRequestDto dto) {
        SurveyEditionResponseDto owner = service.create(dto);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @GetMapping("test/")
    public String test() {
        return "test";
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDto> update(@PathVariable Long id, @RequestBody @Valid SurveyEditionRequestDto dto) {
        SurveyEditionResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

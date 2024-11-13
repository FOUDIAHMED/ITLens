package ahmed.foudi.itlens.controller;

import ahmed.foudi.itlens.dto.answerdto.AnswerRequestDto;
import ahmed.foudi.itlens.dto.answerdto.AnswerResponseDto;
import ahmed.foudi.itlens.dto.response.ProcessResponsesDto;
import ahmed.foudi.itlens.entities.Survey;
import ahmed.foudi.itlens.service.AnswerService;
import ahmed.foudi.itlens.utils.annotations.Exists;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers/")
@RequiredArgsConstructor

class AnswerController {
    private final AnswerService service;

    @GetMapping("all/")
    public ResponseEntity<List<AnswerResponseDto>> findAll() {
        List<AnswerResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> findById(@PathVariable Long id){
        AnswerResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<AnswerResponseDto> create(@RequestBody @Valid AnswerRequestDto dto) {
        AnswerResponseDto question = service.create(dto);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> update(@PathVariable Long id, @RequestBody @Valid AnswerRequestDto dto) {
        AnswerResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{surveyId}/participate")
    public ResponseEntity<Void> participate(@Exists (entityClass = Survey.class,message = "The specified entity does not exist ")@PathVariable Long surveyId, @RequestBody ProcessResponsesDto dto) {
        return ResponseEntity.noContent().build();
    }
}

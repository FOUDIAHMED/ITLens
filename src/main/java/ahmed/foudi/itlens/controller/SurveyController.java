package ahmed.foudi.itlens.controller;

import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyResponseDto;
import ahmed.foudi.itlens.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("surveys/")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService service;

    @GetMapping("all/")
    public ResponseEntity<List<SurveyResponseDto>> findAll() {
        List<SurveyResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> findById(@PathVariable Long id){
        SurveyResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<SurveyResponseDto> create(@RequestBody @Valid SurveyRequestDto dto) {
        SurveyResponseDto owner = service.create(dto);
        System.out.println(owner.getOwner().getName());
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @GetMapping("test/")
    public String test() {
        return "test";
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> update(@PathVariable Long id, @RequestBody @Valid SurveyRequestDto dto) {
        SurveyResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

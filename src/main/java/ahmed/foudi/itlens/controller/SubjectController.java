package ahmed.foudi.itlens.controller;


import ahmed.foudi.itlens.dto.subjectdto.SubjectRequestDto;
import ahmed.foudi.itlens.dto.subjectdto.SubjectResponseDto;
import ahmed.foudi.itlens.dto.surveydto.SurveyRequestDto;
import ahmed.foudi.itlens.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subjects/")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService service;

    @GetMapping("all/")
    public ResponseEntity<List<SubjectResponseDto>> findAll() {
        List<SubjectResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> findById(@PathVariable Long id){
        SubjectResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<SubjectResponseDto> create(@RequestBody @Valid SubjectRequestDto dto) {
        SubjectResponseDto owner = service.create(dto);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @GetMapping("test/")
    public String test() {
        return "test";
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> update(@PathVariable Long id, @RequestBody @Valid SubjectRequestDto dto) {
        SubjectResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

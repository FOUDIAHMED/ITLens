package ahmed.foudi.itlens.controller;

import ahmed.foudi.itlens.dto.ownerdto.OwnerRequestDto;
import ahmed.foudi.itlens.dto.ownerdto.OwnerResponseDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionRequestDto;
import ahmed.foudi.itlens.dto.questiondto.QuestionResponseDto;
import ahmed.foudi.itlens.service.OwnerService;
import ahmed.foudi.itlens.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions/")
@RequiredArgsConstructor

class QuestionController {
    private final QuestionService service;

    @GetMapping("all/")
    public ResponseEntity<List<QuestionResponseDto>> findAll() {
        List<QuestionResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> findById(@PathVariable Long id){
        QuestionResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<QuestionResponseDto> create(@RequestBody @Valid QuestionRequestDto dto) {
        QuestionResponseDto question = service.create(dto);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> update(@PathVariable Long id, @RequestBody @Valid QuestionRequestDto dto) {
        QuestionResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

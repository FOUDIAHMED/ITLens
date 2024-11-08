package ahmed.foudi.itlens.controller;

import ahmed.foudi.itlens.dto.ownerdto.OwnerRequestDto;
import ahmed.foudi.itlens.dto.ownerdto.OwnerResponseDto;
import ahmed.foudi.itlens.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owners/")
@RequiredArgsConstructor

class OwnerController {
    private final OwnerService service;

    @GetMapping("all/")
    public ResponseEntity<List<OwnerResponseDto>> findAll() {
        List<OwnerResponseDto> owners = service.findAll();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> findById(@PathVariable Long id){
        OwnerResponseDto owner = service.findById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("create/")
    public ResponseEntity<OwnerResponseDto> create(@RequestBody @Valid OwnerRequestDto dto) {
        OwnerResponseDto owner = service.create(dto);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @GetMapping("test/")
    public String test() {
        return "test";
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> update(@PathVariable Long id, @RequestBody @Valid OwnerRequestDto dto) {
        OwnerResponseDto owner = service.update(id, dto);
        return ResponseEntity.ok(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

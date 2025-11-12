package java10x.devnoah.desafiocinereview.controller;

import java10x.devnoah.desafiocinereview.dto.ReviewDTO;
import java10x.devnoah.desafiocinereview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // create
    @PostMapping
    public ResponseEntity<ReviewDTO> criar(@RequestBody ReviewDTO dto){
        var saved = reviewService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReviewDTO.builder()
                        .id(saved.getId())
                        .autor(saved.getAutor())
                        .texto(saved.getTexto())
                        .nota(saved.getNota())
                        .createdAt(saved.getCreatedAt())
                        .updatedAt(saved.getUpdatedAt())
                        .build());
    }

    // read - id
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> buscar(@PathVariable UUID id){
        var found = reviewService.buscarPorId(id);
        return ResponseEntity.ok(ReviewDTO.builder()
                .id(found.getId())
                .autor(found.getAutor())
                .texto(found.getTexto())
                .nota(found.getNota())
                .createdAt(found.getCreatedAt())
                .updatedAt(found.getUpdatedAt())
                .build());
    }

    // read - all
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> listar(){
        var list = reviewService.listar().stream().map(l -> ReviewDTO.builder()
                .id(l.getId())
                .autor(l.getAutor())
                .texto(l.getTexto())
                .nota(l.getNota())
                .createdAt(l.getCreatedAt())
                .updatedAt(l.getUpdatedAt())
                .build()).toList();
        return ResponseEntity.ok(list);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> atualizar(@PathVariable UUID id, @RequestBody ReviewDTO dto){
        var exist = ReviewDTO.builder()
                .id(id)
                .autor(dto.autor())
                .texto(dto.texto())
                .nota(dto.nota())
                .build();
        var updated = reviewService.atualizar(exist);
        return ResponseEntity.ok(ReviewDTO.builder()
                .id(updated.getId())
                .autor(updated.getAutor())
                .texto(updated.getTexto())
                .nota(updated.getNota())
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt())
                .build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id){
        reviewService.deletar(id);
    }

}

package java10x.devnoah.desafiocinereview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java10x.devnoah.desafiocinereview.dto.ReviewDTO;
import java10x.devnoah.desafiocinereview.dto.UpdateReviewDTO;
import java10x.devnoah.desafiocinereview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "CRUD de reviews e paginação")
public class ReviewController {

    private final ReviewService reviewService;



    @PostMapping
    @Operation(summary = "Criar review", description = "Cria um review vinculado a um filme via movieId. O movieTitle é obtido automaticamente.")
    public ResponseEntity<ReviewDTO> create(
            @RequestBody(description = "criação do review") @Valid ReviewDTO dto){
        var saved = reviewService.salve(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReviewDTO.builder()
                        .id(saved.getId())
                        .autor(saved.getAutor())
                        .texto(saved.getTexto())
                        .nota(saved.getNota())
                        .movieId(saved.getMovieId())
                        .movieTitle(saved.getMovieTitle())
                        .createdAt(saved.getCreatedAt())
                        .updatedAt(saved.getUpdatedAt())
                        .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar review por ID")
    public ResponseEntity<ReviewDTO> listById(
            @Parameter(description = "ID da review", required = true)
            @PathVariable UUID id){
        var found = reviewService.buscarPorId(id);
        return ResponseEntity.ok(ReviewDTO.builder()
                .id(found.getId())
                .autor(found.getAutor())
                .texto(found.getTexto())
                .nota(found.getNota())
                .movieId(found.getMovieId())
                .movieTitle(found.getMovieTitle())
                .createdAt(found.getCreatedAt())
                .updatedAt(found.getUpdatedAt())
                .build());
    }


//    @GetMapping
//    public ResponseEntity<List<ReviewDTO>> list(){
//        var list = reviewService.listar().stream().map(l -> ReviewDTO.builder()
//                .id(l.getId())
//                .autor(l.getAutor())
//                .texto(l.getTexto())
//                .nota(l.getNota())
//                .movieId(l.getMovieId())
//                .movieTitle(l.getMovieTitle())
//                .createdAt(l.getCreatedAt())
//                .updatedAt(l.getUpdatedAt())
//                .build()).toList();
//        return ResponseEntity.ok(list);
//    }

    @GetMapping
    @Operation(summary = "Listar reviews paginados", description = "Retorna reviews paginados ordenados por createdAt ASC")

    public ResponseEntity<Page<ReviewDTO>> listPages(
            @Parameter(description = "Número da página (0)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página (10)")
            @RequestParam(defaultValue = "10") int size ){
        Page<ReviewDTO> reviewPage = reviewService.listPages(page, size);
        return ResponseEntity.ok(reviewPage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar review", description = "Atualiza apenas nota (0-10) e texto. movieId não pode ser alterado.")

    public ResponseEntity<ReviewDTO> update(
            @Parameter(description = "ID da review", required = true)
            @PathVariable UUID id,
            @RequestBody(description = "Atualização (somente nota e texto)", required = true) @Valid UpdateReviewDTO dto){
        var updated = reviewService.update(id, dto);
        return ResponseEntity.ok(ReviewDTO.builder()
                .id(updated.getId())
                .autor(updated.getAutor())
                .texto(updated.getTexto())
                .nota(updated.getNota())
                .movieId(updated.getMovieId())
                .movieTitle(updated.getMovieTitle())
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt())
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir review")
    public void delete(
            @Parameter(description = "ID da review", required = true)
            @PathVariable UUID id){
        reviewService.delete(id);
    }
}

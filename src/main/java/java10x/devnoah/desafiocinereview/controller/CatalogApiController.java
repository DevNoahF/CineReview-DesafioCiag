package java10x.devnoah.desafiocinereview.controller;

import java10x.devnoah.desafiocinereview.controller.response.MovieResponse;
import java10x.devnoah.desafiocinereview.service.CatalogApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Busca de filmes via TMDB")
public class CatalogApiController {
    private final CatalogApiService catalogApiService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar filmes por título", description = "Pesquisa filmes no TMDB pelo título informado")
    public ResponseEntity<List<MovieResponse>> searchMovie(
            @Parameter(description = "Título a pesquisar", required = true)
            @RequestParam String title){
        return ResponseEntity.ok(catalogApiService.searchMovie(title));
    }
}

package java10x.devnoah.desafiocinereview.service;

import java10x.devnoah.desafiocinereview.dto.ReviewDTO;
import java10x.devnoah.desafiocinereview.mapper.ReviewMapper;
import java10x.devnoah.desafiocinereview.model.Review;
import java10x.devnoah.desafiocinereview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieService movieService;

    private void validarNota(double nota) {
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
        }
    }

    private void vincularFilmePorTitulo(Review entity) {
        String title = entity.getMovieTitle();
        if (title == null) {
            throw new IllegalArgumentException("O título do filme é obrigatório");
        }
        var resultados = movieService.searchMovie(title);
        if (resultados.isEmpty()) {
            throw new IllegalArgumentException("Filme não encontrado para o título:");
        }
        var escolhido = resultados.stream()
                .filter(m -> entity.getMovieId() != null && m.id() == entity.getMovieId())
                .findFirst()
                .orElse(resultados.get(0));

        entity.setMovieId(escolhido.id());
        entity.setMovieTitle(escolhido.title());
    }

    public Review salvar(ReviewDTO dto){
        validarNota(dto.nota());
        Review entity = reviewMapper.toEntity(dto);
        vincularFilmePorTitulo(entity);
        return reviewRepository.save(entity);
    }

    public Review buscarPorId(UUID id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada: " + id));
    }


    public List<Review> listar(){
        return reviewRepository.findAll();
    }



    public Review atualizar(ReviewDTO dto){
        if (dto.id() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualização");
        }
        validarNota(dto.nota());
        Review existente = reviewRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada:"));


        existente.setAutor(dto.autor());
        existente.setTexto(dto.texto());
        existente.setNota(dto.nota());
        existente.setMovieId(dto.movieId());
        existente.setMovieTitle(dto.movieTitle());
        vincularFilmePorTitulo(existente);

        return reviewRepository.save(existente);
    }

    public void deletar(UUID id){
        if (!reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("Review não encontrada: " + id);
        }
        reviewRepository.deleteById(id);
    }
}

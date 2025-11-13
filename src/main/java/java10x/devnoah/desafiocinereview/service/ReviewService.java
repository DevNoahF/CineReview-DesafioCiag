package java10x.devnoah.desafiocinereview.service;

import java10x.devnoah.desafiocinereview.dto.ReviewDTO;
import java10x.devnoah.desafiocinereview.dto.UpdateReviewDTO;
import java10x.devnoah.desafiocinereview.mapper.ReviewMapper;
import java10x.devnoah.desafiocinereview.model.Review;
import java10x.devnoah.desafiocinereview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final CatalogApiService catalogApiService;

    private void movieById(Review entity) {
        Integer movieId = entity.getMovieId();
        if (movieId == null) {
            throw new IllegalArgumentException("movieId é obrigatório");
        }
        var movie = catalogApiService.getMovieById(movieId);
        entity.setMovieTitle(movie.title());
    }

    public Review salve(ReviewDTO dto){
        // TODO FIX: erro detached entity ao enviar id

        if (dto.id() != null) {
            throw new IllegalArgumentException("ID não deve ser enviado na criação da review");
        }
        Review entity = reviewMapper.toEntity(dto);
        movieById(entity);
        return reviewRepository.save(entity);
    }

    public Review buscarPorId(UUID id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada "));
    }

    public Review searchAutor(String autor){
        return reviewRepository.findByAutor(autor)
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada para o autor:"));
    }

    // regra: so pode dar update em texto e nota
    public Review update(UUID id, UpdateReviewDTO dto){
        Review existente = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada"));

        existente.setTexto(dto.texto());
        existente.setNota(dto.nota());

        return reviewRepository.save(existente);
    }

    public void delete(UUID id){
        if (!reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("Review não encontrada ");
        }
        reviewRepository.deleteById(id);
    }


    public Page<ReviewDTO> listPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"createdAt"));
        return reviewRepository.findAll(pageable).map(reviewMapper::toDTO);
    }
}

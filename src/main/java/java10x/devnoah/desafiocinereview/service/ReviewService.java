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



    // create
    public Review salvar(ReviewDTO dto){
        Review entity = reviewMapper.toEntity(dto);
        return reviewRepository.save(entity);
    }

    // read -  id
    public Review buscarPorId(UUID id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada: "));
    }

    // read - all
    public List<Review> listar(){
        return reviewRepository.findAll();
    }


    // update
    public Review atualizar(ReviewDTO dto){
        if (dto.id() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualização");
        }
        Review existente = reviewRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Review não encontrada: "));


        existente.setTexto(dto.texto());
        existente.setNota(dto.nota());

        return reviewRepository.save(existente);
    }

    // delete
    public void deletar(UUID id){
        if (reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("Review não encontrada: ");
        }
        reviewRepository.deleteById(id);
    }
}

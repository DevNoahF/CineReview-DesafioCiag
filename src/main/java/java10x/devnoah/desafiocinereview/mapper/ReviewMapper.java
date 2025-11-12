package java10x.devnoah.desafiocinereview.mapper;

import java10x.devnoah.desafiocinereview.dto.ReviewDTO;
import java10x.devnoah.desafiocinereview.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    // to Entity
    public Review toEntity(ReviewDTO dto){
        return Review.builder()
                .id(dto.id())
                .autor(dto.autor())
                .texto(dto.texto())
                .nota(dto.nota())
                .build();
    }

    // to DTO
    public ReviewDTO toDTO(Review entity){
        return ReviewDTO.builder()
                .id(entity.getId())
                .autor(entity.getAutor())
                .texto(entity.getTexto())
                .nota(entity.getNota())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

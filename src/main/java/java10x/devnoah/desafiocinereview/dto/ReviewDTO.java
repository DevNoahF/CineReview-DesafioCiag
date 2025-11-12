package java10x.devnoah.desafiocinereview.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;
@Builder
public record ReviewDTO(
        UUID id,
        String autor,
        @Lob String texto,
        @DecimalMin(value = "0.0", inclusive = true, message = "A nota deve ser no min 0")
        @DecimalMax(value = "10.0", inclusive = true, message = "A nota deve ser no max 10")
        double nota,
        Integer movieId,
        String movieTitle,
        Instant createdAt,
        Instant updatedAt
) {
}

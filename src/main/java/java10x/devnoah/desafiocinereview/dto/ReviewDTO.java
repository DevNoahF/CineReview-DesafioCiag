package java10x.devnoah.desafiocinereview.dto;

import jakarta.persistence.Lob;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;
@Builder
public record ReviewDTO(
        UUID id,
        String autor,
        @Lob String texto,
        double nota,
        Instant createdAt,
        Instant updatedAt
) {
}

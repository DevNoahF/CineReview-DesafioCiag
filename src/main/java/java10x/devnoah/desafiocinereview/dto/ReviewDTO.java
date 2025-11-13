package java10x.devnoah.desafiocinereview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;
@Builder
public record ReviewDTO(
        UUID id,
        @NotNull @Size(max = 30) String autor,
        @Lob String texto,
        @NotNull @Min(0) @Max(10) double nota,
        @NotNull Integer movieId,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) String movieTitle,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant createdAt,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant updatedAt
) {
}

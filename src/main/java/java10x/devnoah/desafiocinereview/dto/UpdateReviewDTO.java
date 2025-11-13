package java10x.devnoah.desafiocinereview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateReviewDTO(
        @Min(0) @Max(10) double nota,
        @NotBlank String texto
) {}


package java10x.devnoah.desafiocinereview.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDTO(
        int id,
        String title,
        String overview,
        @JsonProperty("poster_path")
        String poster,
        @JsonProperty("vote_average")
        double rating
) {
}

package java10x.devnoah.desafiocinereview.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CatalogApiResponse(
        @JsonProperty("results") List<MovieResponse> movies
) {
}

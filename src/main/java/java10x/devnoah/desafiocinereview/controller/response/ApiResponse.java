package java10x.devnoah.desafiocinereview.controller.response;

import java10x.devnoah.desafiocinereview.dto.MovieDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonProperty("results")
        List<MovieDTO> movies
) {
}

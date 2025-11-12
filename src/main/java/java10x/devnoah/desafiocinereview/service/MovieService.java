package java10x.devnoah.desafiocinereview.service;

import java10x.devnoah.desafiocinereview.dto.MovieDTO;
import java10x.devnoah.desafiocinereview.controller.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService {


    private final String BASE_URL = "https://api.themoviedb.org/3/search/movie";

    @Value("${API_KEY}")
    private String apiKey=System.getenv("API_KEY");

    public List<MovieDTO> searchMovie(String title) {
        RestTemplate restTemplate = new RestTemplate();


        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .queryParam("include_adult", false)
                .queryParam("query", title)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, ApiResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().movies();
        }
        return Collections.emptyList();
    }
}

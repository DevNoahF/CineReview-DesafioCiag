package java10x.devnoah.desafiocinereview.controller;

import java10x.devnoah.desafiocinereview.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String serchMovie(@RequestParam String title){
        ;
        return movieService.searchMovie(title).toString();
    }

}

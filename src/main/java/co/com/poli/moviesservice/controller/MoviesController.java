package co.com.poli.moviesservice.controller;

import co.com.poli.moviesservice.helpers.Response;
import co.com.poli.moviesservice.helpers.ResponseBuild;
import co.com.poli.moviesservice.persistence.entity.Movies;
import co.com.poli.moviesservice.service.MoviesService;
import co.com.poli.moviesservice.service.dto.MoviesInDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesService moviesService;
    private final ResponseBuild responseBuild;

    @Autowired
    public MoviesController(MoviesService moviesService, ResponseBuild responseBuild) {
        this.moviesService = moviesService;
        this.responseBuild = responseBuild;
    }

    @PostMapping
    public Response saveMovie(@Valid @RequestBody MoviesInDTO moviesInDTO, BindingResult result) {
        if (result.hasErrors()) {
            return this.responseBuild.failed(formatMessage(result));
        }

        return this.responseBuild.successCreated(this.moviesService.save(moviesInDTO));
    }

    @DeleteMapping("/{id}")
    public Response deleteMovie(@PathVariable("id") Long id) {
        String message = this.moviesService.delete(id);
        if (message.equals("eliminado")) {
            return this.responseBuild.success("La película con id:" + id + " fue eliminada.");
        } else {
            return this.responseBuild.failedNotFound("La película con id:" + id + " no existe.");
        }
    }

    @GetMapping
    public Response findAllMovies() {
        List<Movies> movies = this.moviesService.findAll();
        if (!movies.isEmpty()) {
            return this.responseBuild.success(movies);
        } else {
            return this.responseBuild.failedNotFound("No existen películas registradas");
        }
    }

    @GetMapping("/{id}")
    public Response findMovieById(@PathVariable("id") Long id) {
        Movies movie = this.moviesService.findById(id);
        if (movie != null) {
            return this.responseBuild.success(movie);
        } else {
            return this.responseBuild.failedNotFound("No existe una película para el id:" + id);
        }
    }

    private List<Map<String, String>> formatMessage(BindingResult result) {

        return null;
    }
}

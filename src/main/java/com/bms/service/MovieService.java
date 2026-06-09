package com.bms.service;
import com.bms.dto.MovieRequest;
import com.bms.dto.MovieResponse;
import com.bms.entity.Movie;
import java.util.List;

public interface MovieService {
    MovieResponse addMovie(MovieRequest request);
    MovieResponse getMovieById(Long id);
    List<MovieResponse> getAllMovies();
    List<MovieResponse> searchByTitle(String title);
    List<MovieResponse> getByGenre(String genre);
    List<MovieResponse> getByLanguage(String language);
    MovieResponse updateMovie(Long id, MovieRequest request);
    void deleteMovie(Long id);
    Movie findEntityById(Long id);
}

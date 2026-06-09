package com.bms.service;
import com.bms.dto.MovieRequest;
import com.bms.dto.MovieResponse;
import com.bms.entity.Movie;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle()).description(request.getDescription())
                .genre(request.getGenre()).language(request.getLanguage())
                .durationMinutes(request.getDurationMinutes()).rating(request.getRating())
                .posterUrl(request.getPosterUrl()).releaseDate(request.getReleaseDate())
                .build();
        return toResponse(movieRepository.save(movie));
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<MovieResponse> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toResponse).toList();
    }

    @Override
    public List<MovieResponse> getByGenre(String genre) {
        return movieRepository.findByGenreIgnoreCase(genre).stream().map(this::toResponse).toList();
    }

    @Override
    public List<MovieResponse> getByLanguage(String language) {
        return movieRepository.findByLanguageIgnoreCase(language).stream().map(this::toResponse).toList();
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = findEntityById(id);
        movie.setTitle(request.getTitle()); movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre()); movie.setLanguage(request.getLanguage());
        movie.setDurationMinutes(request.getDurationMinutes()); movie.setRating(request.getRating());
        movie.setPosterUrl(request.getPosterUrl()); movie.setReleaseDate(request.getReleaseDate());
        return toResponse(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) throw new ResourceNotFoundException("Movie", id);
        movieRepository.deleteById(id);
    }

    @Override
    public Movie findEntityById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", id));
    }

    private MovieResponse toResponse(Movie m) {
        return MovieResponse.builder()
                .id(m.getId()).title(m.getTitle()).description(m.getDescription())
                .genre(m.getGenre()).language(m.getLanguage())
                .durationMinutes(m.getDurationMinutes()).rating(m.getRating())
                .posterUrl(m.getPosterUrl()).releaseDate(m.getReleaseDate())
                .build();
    }
}

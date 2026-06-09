package com.bms.repository;
import com.bms.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenreIgnoreCase(String genre);
    List<Movie> findByLanguageIgnoreCase(String language);
    List<Movie> findByTitleContainingIgnoreCase(String title);
}

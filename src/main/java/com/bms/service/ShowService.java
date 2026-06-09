package com.bms.service;
import com.bms.dto.ShowRequest;
import com.bms.dto.ShowResponse;
import com.bms.entity.Show;
import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    ShowResponse addShow(ShowRequest request);
    ShowResponse getShowById(Long id);
    List<ShowResponse> getAllShows();
    List<ShowResponse> getShowsByMovie(Long movieId);
    List<ShowResponse> getShowsByMovieAndDate(Long movieId, LocalDate date);
    Show findEntityById(Long id);
}

package com.bms.service;
import com.bms.dto.ShowRequest;
import com.bms.dto.ShowResponse;
import com.bms.entity.Movie;
import com.bms.entity.Screen;
import com.bms.entity.Show;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final MovieServiceImpl movieService;
    private final ScreenServiceImpl screenService;

    @Override
    public ShowResponse addShow(ShowRequest request) {
        Movie movie = movieService.findEntityById(request.getMovieId());
        Screen screen = screenService.findEntityById(request.getScreenId());
        Show show = Show.builder()
                .movie(movie).screen(screen)
                .showDate(request.getShowDate())
                .startTime(request.getStartTime()).endTime(request.getEndTime())
                .ticketPrice(request.getTicketPrice())
                .build();
        return toResponse(showRepository.save(show));
    }

    @Override
    public ShowResponse getShowById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public List<ShowResponse> getAllShows() {
        return showRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<ShowResponse> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId).stream().map(this::toResponse).toList();
    }

    @Override
    public List<ShowResponse> getShowsByMovieAndDate(Long movieId, LocalDate date) {
        return showRepository.findByMovieIdAndShowDate(movieId, date).stream().map(this::toResponse).toList();
    }

    @Override
    public Show findEntityById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show", id));
    }

    private ShowResponse toResponse(Show s) {
        return ShowResponse.builder()
                .id(s.getId()).screenId(s.getScreen().getId())
                .movieTitle(s.getMovie().getTitle())
                .screenName(s.getScreen().getName())
                .theaterName(s.getScreen().getTheater().getName())
                .cityName(s.getScreen().getTheater().getCity().getName())
                .showDate(s.getShowDate()).startTime(s.getStartTime()).endTime(s.getEndTime())
                .ticketPrice(s.getTicketPrice())
                .build();
    }
}

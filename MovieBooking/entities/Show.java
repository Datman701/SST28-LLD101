package entities;

import java.time.LocalDateTime;

public class Show {
    public String id;
    public Movie movie;
    public Screen screen;
    public LocalDateTime startTime;

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
    }
}

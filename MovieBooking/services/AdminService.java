package services;

import entities.City;
import entities.Movie;
import entities.Show;
import entities.Theatre;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private final List<City> cities = new ArrayList<>();
    private final List<Movie> movies = new ArrayList<>();
    private final List<Show> shows = new ArrayList<>();

    public void addCity(City city) {
        cities.add(city);
    }

    public void addTheatre(Theatre theatre) {
        theatre.city.theatres.add(theatre);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Show> getShows() {
        return shows;
    }
}

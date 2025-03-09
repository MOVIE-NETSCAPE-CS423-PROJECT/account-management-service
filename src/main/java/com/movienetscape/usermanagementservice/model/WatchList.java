package com.movienetscape.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @OneToMany(mappedBy = "watchList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WatchListMovie> watchListMovies = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public void addMovieToWatchList(Long movieId) {
        WatchListMovie movie = new WatchListMovie();
        movie.setWatchList(this);
        movie.setMovieId(movieId);
        this.watchListMovies.add(movie);
    }

    public void removeMovieFromWatchList(Long movieId) {
        watchListMovies.removeIf(movie -> movie.getMovieId().equals(movieId));
    }

    public void clearWatchList() {
        watchListMovies.clear();
    }
}

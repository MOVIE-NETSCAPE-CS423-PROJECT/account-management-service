package com.movienetscape.accountmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile extends BaseEntity {

    @Column(nullable = false)
    private String profileName;

    @Column
    private String profileDescription;

    @Column
    private String profileImageUrl;

    @Column
    private String profileType;

    @Column
    private int maxMovies;


    @Column(nullable = false)
    private boolean isPrimaryProfile;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchListMovie> watchListMovies = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavouriteMovie> favouriteMovies = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoughtMovie> boughtMovies = new ArrayList<>();

    @Column(nullable = false)
    private boolean hasParentalControl;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_id")
    @JsonBackReference
    private Account account;


    @Override
    protected String generateId() {
        return "mns-pr-" + UUID.randomUUID().toString().substring(0, 7);
    }


    public boolean canAddToWatchList() {
        return maxMovies > 0 && watchListMovies.size() < maxMovies;
    }


    public void removeMovieFromWatchList(WatchListMovie watchListMovie) {
        watchListMovies.remove(watchListMovie);
    }

    public void addMovieToWatchList(WatchListMovie watchListMovie) {
        watchListMovies.add(watchListMovie);
    }

    public void removeMovieFromFavouriteList(FavouriteMovie favouriteMovie) {
        favouriteMovies.remove(favouriteMovie);
    }

    public void addMovieToFavouriteList(FavouriteMovie favouriteMovie) {
        favouriteMovies.add(favouriteMovie);
    }


    public void addMovieToBoughtList(BoughtMovie boughtMovie) {
        boughtMovies.add(boughtMovie);
    }

    public void toggleParentalControl() {
        this.hasParentalControl = !this.hasParentalControl;
    }

    public void clearWatchList() {
        watchListMovies.clear();
    }

    public void clearFavouriteMovies() {
        favouriteMovies.clear();
    }
}

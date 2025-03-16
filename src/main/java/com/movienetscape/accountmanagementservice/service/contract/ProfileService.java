package com.movienetscape.accountmanagementservice.service.contract;


import com.movienetscape.accountmanagementservice.dto.request.MovieRequest;
import com.movienetscape.accountmanagementservice.dto.response.MovieResponse;
import com.movienetscape.accountmanagementservice.model.BoughtMovie;
import com.movienetscape.accountmanagementservice.model.FavouriteMovie;
import com.movienetscape.accountmanagementservice.model.WatchListMovie;

import java.util.List;

public interface ProfileService {


    List<MovieResponse> getProfileAllFavouriteMovies(String profileId);

    MovieResponse addProfileFavouriteMovie(MovieRequest movieRequest);

    List<MovieResponse> getProfileAllBoughtMovies(String profileId);


    MovieResponse addProfileBoughtMovie(MovieRequest movieRequest);


    List<MovieResponse> getAllWatchListMovies(String profileId);


    MovieResponse addProfileWatchListMovie(MovieRequest movieRequest);


    String removeProfileWatchListMovie(MovieRequest movieRequest);

    String removeProfileFavouriteMovie(MovieRequest movieRequest);
}

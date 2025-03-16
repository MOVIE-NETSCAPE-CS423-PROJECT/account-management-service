package com.movienetscape.accountmanagementservice.service.impl;

import com.movienetscape.accountmanagementservice.dto.request.MovieRequest;
import com.movienetscape.accountmanagementservice.dto.response.MovieResponse;
import com.movienetscape.accountmanagementservice.model.BoughtMovie;
import com.movienetscape.accountmanagementservice.model.FavouriteMovie;
import com.movienetscape.accountmanagementservice.model.Profile;
import com.movienetscape.accountmanagementservice.model.WatchListMovie;
import com.movienetscape.accountmanagementservice.repository.ProfileRepository;
import com.movienetscape.accountmanagementservice.service.contract.ProfileService;
import com.movienetscape.accountmanagementservice.util.exception.DuplicateException;
import com.movienetscape.accountmanagementservice.util.exception.IllegalOperationException;
import com.movienetscape.accountmanagementservice.util.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<MovieResponse> getProfileAllFavouriteMovies(String profileId) {
        Profile profile = profileRepository
                .findProfileWithFavouriteMovies(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return profile.getFavouriteMovies().stream()
                .map(favouriteMovie -> new MovieResponse(
                        favouriteMovie.getMovieTitle(),
                        favouriteMovie.getDateAdded(),
                        favouriteMovie.getMovieId(),
                        profile.getProfileName()
                ))
                .toList();
    }


    @Override
    public MovieResponse addProfileFavouriteMovie(MovieRequest movieRequest) {

        Profile profile = profileRepository
                .findProfileWithFavouriteMovies(movieRequest.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));


        boolean exists = profile.getFavouriteMovies().stream()
                .anyMatch(movie -> movie.getMovieId().equals(movieRequest.getMovieId()));
        if (exists) {
            throw new DuplicateException("Movie already exists");
        }


        FavouriteMovie favouriteMovie = new FavouriteMovie();
        favouriteMovie.setProfile(profile);
        favouriteMovie.setMovieId(movieRequest.getMovieId());
        favouriteMovie.setDateAdded(LocalDate.now());
        favouriteMovie.setMovieTitle(movieRequest.getTitle());

        profile.addMovieToFavouriteList(favouriteMovie);


        Profile savedProfile = profileRepository.save(profile);


        FavouriteMovie savedMovie = savedProfile.getFavouriteMovies()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieRequest.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to save movie"));


        return MovieResponse.builder()
                .title(savedMovie.getMovieTitle())
                .movieId(savedMovie.getMovieId())
                .profileName(savedProfile.getProfileName())
                .dateAdded(savedMovie.getDateAdded())
                .build();
    }

    @Override
    public List<MovieResponse> getProfileAllBoughtMovies(String profileId) {

        Profile profile = profileRepository
                .findProfileWithFavouriteMovies(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return profile.getBoughtMovies().stream()
                .map(boughtMovie -> new MovieResponse(
                        boughtMovie.getMovieTitle(),
                        boughtMovie.getDateAdded(),
                        boughtMovie.getMovieId(),
                        profile.getProfileName()
                ))
                .toList();
    }

    @Override
    public MovieResponse addProfileBoughtMovie(MovieRequest movieRequest) {


        Profile profile = profileRepository
                .findProfileWithBoughtMovies(movieRequest.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));


        boolean exists = profile.getBoughtMovies().stream()
                .anyMatch(movie -> movie.getMovieId().equals(movieRequest.getMovieId()));
        if (exists) {
            throw new DuplicateException("Movie already exists");
        }


        BoughtMovie boughtMovie = new BoughtMovie();
        boughtMovie.setProfile(profile);
        boughtMovie.setMovieId(movieRequest.getMovieId());
        boughtMovie.setDateAdded(LocalDate.now());
        boughtMovie.setMovieTitle(movieRequest.getTitle());

        profile.addMovieToBoughtList(boughtMovie);


        Profile savedProfile = profileRepository.save(profile);


        BoughtMovie savedMovie = savedProfile.getBoughtMovies()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieRequest.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to save movie"));


        return MovieResponse.builder()
                .title(savedMovie.getMovieTitle())
                .movieId(savedMovie.getMovieId())
                .profileName(savedProfile.getProfileName())
                .dateAdded(savedMovie.getDateAdded())
                .build();
    }

    @Override
    public List<MovieResponse> getAllWatchListMovies(String profileId) {

        Profile profile = profileRepository
                .findProfileWithWatchListMovies(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return profile.getWatchListMovies().stream()
                .map(watchListMovie -> new MovieResponse(
                        watchListMovie.getMovieTitle(),
                        watchListMovie.getDateAdded(),
                        watchListMovie.getMovieId(),
                        profile.getProfileName()
                ))
                .toList();
    }

    @Override
    public MovieResponse addProfileWatchListMovie(MovieRequest movieRequest) {


        Profile profile = profileRepository
                .findProfileWithWatchListMovies(movieRequest.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));


        boolean exists = profile.getWatchListMovies().stream()
                .anyMatch(movie -> movie.getMovieId().equals(movieRequest.getMovieId()));
        if (exists) {
            throw new DuplicateException("Movie already exists");
        }

        if (!profile.canAddToWatchList()) throw new IllegalOperationException("Can't add to already full watch list");

        WatchListMovie watchListMovie = new WatchListMovie();
        watchListMovie.setProfile(profile);
        watchListMovie.setMovieId(movieRequest.getMovieId());
        watchListMovie.setDateAdded(LocalDate.now());
        watchListMovie.setMovieTitle(movieRequest.getTitle());

        profile.addMovieToWatchList(watchListMovie);


        Profile savedProfile = profileRepository.save(profile);


        WatchListMovie savedMovie = savedProfile.getWatchListMovies()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieRequest.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to save movie"));


        return MovieResponse.builder()
                .title(savedMovie.getMovieTitle())
                .movieId(savedMovie.getMovieId())
                .profileName(savedProfile.getProfileName())
                .dateAdded(savedMovie.getDateAdded())
                .build();
    }

    @Override
    public String removeProfileWatchListMovie(MovieRequest movieRequest) {


        Profile profile = profileRepository
                .findProfileWithWatchListMovies(movieRequest.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        WatchListMovie watchListMovie = profile.getWatchListMovies().stream()
                .filter((movie -> movie.getMovieId().equals(movieRequest.getMovieId())))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        profile.removeMovieFromWatchList(watchListMovie);
        profileRepository.save(profile);

        return "Movie removed from watchlist";
    }

    @Override
    public String removeProfileFavouriteMovie(MovieRequest movieRequest) {


        Profile profile = profileRepository
                .findProfileWithFavouriteMovies(movieRequest.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        FavouriteMovie favouriteMovie = profile.getFavouriteMovies().stream()
                .filter((movie -> movie.getMovieId().equals(movieRequest.getMovieId())))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        profile.removeMovieFromFavouriteList(favouriteMovie);
        profileRepository.save(profile);

        return "Movie removed from favourite list";
    }


}
package com.movienetscape.accountmanagementservice.controller;


import com.movienetscape.accountmanagementservice.dto.request.MovieRequest;
import com.movienetscape.accountmanagementservice.dto.response.MovieResponse;
import com.movienetscape.accountmanagementservice.service.contract.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movielists")
@RequiredArgsConstructor
public class ProfileController {


    private final ProfileService movieService;

    @GetMapping("/favourites/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllFavouriteMovies(@PathVariable String profileId) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getProfileAllFavouriteMovies(profileId));
    }


    @PostMapping("/favourites")
    public ResponseEntity<MovieResponse> addFavouriteMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.addProfileFavouriteMovie(movieRequest));
    }


    @PostMapping("{/favourites/remove")
    public ResponseEntity<String> removeFavouriteMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.removeProfileFavouriteMovie(movieRequest));
    }


    @GetMapping("/bought/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllBoughtMovies(@PathVariable String profileId) {
        return ResponseEntity.ok(movieService.getProfileAllBoughtMovies(profileId));
    }


    @PostMapping("/bought")
    public ResponseEntity<MovieResponse> addBoughtMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.addProfileBoughtMovie(movieRequest));
    }


    @GetMapping("/watchlist/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllWatchListMovies(@PathVariable String profileId) {
        return ResponseEntity.ok(movieService.getAllWatchListMovies(profileId));
    }

    @PostMapping("/watchlist")
    public ResponseEntity<MovieResponse> addWatchListMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.addProfileWatchListMovie(movieRequest));
    }

    @PostMapping("/watchlist/remove")
    public ResponseEntity<MovieResponse> removeWatchListMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.addProfileWatchListMovie(movieRequest));
    }
}


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
@RequestMapping("api/v1/profile-movies")
@RequiredArgsConstructor
public class ProfileController {


    private final ProfileService profileService;

    @GetMapping("/favourites/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllFavouriteMovies(@PathVariable String profileId) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileAllFavouriteMovies(profileId));
    }


    @PostMapping("/favourites")
    public ResponseEntity<MovieResponse> addFavouriteMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(profileService.addProfileFavouriteMovie(movieRequest));
    }


    @DeleteMapping("/favourites")
    public ResponseEntity<String> removeFavouriteMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(profileService.removeProfileFavouriteMovie(movieRequest));
    }


    @GetMapping("/bought/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllBoughtMovies(@PathVariable String profileId) {
        return ResponseEntity.ok(profileService.getProfileAllBoughtMovies(profileId));
    }


    @PostMapping("/bought")
    public ResponseEntity<MovieResponse> addBoughtMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(profileService.addProfileBoughtMovie(movieRequest));
    }


    @GetMapping("/watchlist/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllWatchListMovies(@PathVariable String profileId) {
        return ResponseEntity.ok(profileService.getAllWatchListMovies(profileId));
    }

    @PostMapping("/watchlist")
    public ResponseEntity<MovieResponse> addWatchListMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(profileService.addProfileWatchListMovie(movieRequest));
    }

    @DeleteMapping("/watchlist")
    public ResponseEntity<String> removeWatchListMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return ResponseEntity.ok(profileService.removeProfileWatchListMovie(movieRequest));
    }
}


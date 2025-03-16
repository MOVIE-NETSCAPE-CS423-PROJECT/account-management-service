package com.movienetscape.accountmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListMovie extends BaseMovie {

    @Column(nullable = false)
    private boolean hasStartedWatching = false;


    public void setMovieDetails(String title, LocalDate date, Profile profile) {
        setMovieTitle(title);
        setDateAdded(date);
        setProfile(profile);
    }

    @Override
    public String generateId() {
        return "wlm-act-" + UUID.randomUUID().toString().substring(0, 7);
    }
}





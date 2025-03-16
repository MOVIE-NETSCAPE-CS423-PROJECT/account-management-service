package com.movienetscape.accountmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@MappedSuperclass
@Data
public abstract class BaseMovie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonBackReference
    private  Profile profile;

    @Column(nullable = false)
    private  String movieId;

    @Column(nullable = false)
    private  String movieTitle;

    @Column(nullable = false)
    private LocalDate dateAdded;


    protected abstract String generateId();
}





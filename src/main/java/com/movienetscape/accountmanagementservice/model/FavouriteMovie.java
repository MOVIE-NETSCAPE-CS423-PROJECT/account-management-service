package com.movienetscape.accountmanagementservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;



@Entity
@Data
@AllArgsConstructor
public class FavouriteMovie extends BaseMovie {

    @Override
    public String generateId() {
        return "fav-mov-" + UUID.randomUUID().toString().substring(0, 7);
    }
}



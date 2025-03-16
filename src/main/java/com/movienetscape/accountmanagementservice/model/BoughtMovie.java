package com.movienetscape.accountmanagementservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoughtMovie extends BaseMovie {


    @Column(nullable = false)
    private LocalDate dateBought;

    @Column(nullable = false)
    private boolean canDownloadOnDevice = false;

    @Override
    public String generateId() {
        return "buy-mov-" + UUID.randomUUID().toString().substring(0, 7);
    }


}



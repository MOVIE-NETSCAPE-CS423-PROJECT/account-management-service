package com.movienetscape.accountmanagementservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private String title;
    private LocalDate dateAdded;
    private String movieId;
    private String profileName;

}

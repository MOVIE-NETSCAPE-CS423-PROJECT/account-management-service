package com.movienetscape.accountmanagementservice.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieRequest {

    @NotNull(message = "ProfileId cant be null")
    @NotEmpty(message = "ProfileId cant be empty")
    private String profileId;

    @NotNull(message = "MovieId cant be null")

    @NotEmpty(message = "MovieId cant be empty")
    private String movieId;

    private String title;

    private LocalDate buyDate;


}

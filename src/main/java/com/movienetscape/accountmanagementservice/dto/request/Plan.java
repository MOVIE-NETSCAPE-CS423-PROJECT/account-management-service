package com.movienetscape.accountmanagementservice.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    @JsonProperty(required = true)
    @NotBlank(message = "Plan name is required and cannot be blank.")
    private String planName;

    @JsonProperty(required = true)
    @NotBlank(message = "Max movies per profile on subscription  is required and cannot be blank.")
    private Integer maxMoviesPerProfileOnActiveSubscription;

    @JsonProperty(required = true)
    @NotBlank(message = "Max movies per profile on  no subscription  is required and cannot be blank.")
    private Integer maxMoviesPerProfileOnNotActiveSubscription;
}

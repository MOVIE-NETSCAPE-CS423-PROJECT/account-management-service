package com.movienetscape.accountmanagementservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    @JsonProperty(required = true)
    @NotBlank(message = "User ID is required and cannot be blank.")
    private String userId;

    @JsonProperty(required = true)
    @NotBlank(message = "First name is required and cannot be blank.")
    private String firstName;

    @JsonProperty(required = true)
    @NotBlank(message = "Last name is required and cannot be blank.")
    private String lastName;



    @JsonProperty(required = true)
    @NotNull(message = "User selected plan is required.")
    private Plan userSelectedPlan;
}

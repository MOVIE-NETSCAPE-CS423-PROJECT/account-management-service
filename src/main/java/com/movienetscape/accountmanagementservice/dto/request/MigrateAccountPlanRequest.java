package com.movienetscape.accountmanagementservice.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrateAccountPlanRequest {

    @JsonProperty(required = true)
    @NotBlank(message = "AccountId is required and cannot be blank.")
    private String accountId;

    @JsonProperty(required = true)
    @NotBlank(message = "Current plan name is required and cannot be blank.")
    private String currentPlanName;

    @JsonProperty(required = true)
    @NotBlank(message = "New plan name is required and cannot be blank.")
    private String newPlanName;
}

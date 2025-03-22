package com.movienetscape.accountmanagementservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {
    @NotBlank
    private String profileName;
    @NotBlank
    private String profileDescription;
    @NotBlank
    private String profileImageUrl;
    @NotBlank
    private String profileType;
    @NotNull
    private boolean hasParentalControl;
    @NotNull
    private boolean isPrimaryProfile;

}

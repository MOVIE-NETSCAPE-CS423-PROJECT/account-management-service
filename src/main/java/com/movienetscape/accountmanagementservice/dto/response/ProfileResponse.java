package com.movienetscape.accountmanagementservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProfileResponse {
    private String profileId;
    private String profileName;
    private String profileDescription;
    private String profileImageUrl;
    private String profileType;
    private boolean hasParentalControl;
    private boolean isPrimaryProfile;
}

package com.movienetscape.accountmanagementservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {
    private String profileName;
    private String profileDescription;
    private String profileImageUrl;
    private String profileType;
    private boolean hasParentalControl;
    private boolean isPrimaryProfile;

}

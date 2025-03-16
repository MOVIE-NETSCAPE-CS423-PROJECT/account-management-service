package com.movienetscape.accountmanagementservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddProfileResponse {

    private String profileId;
    private String profileName;
    private String profileDescription;
    private String profileImageUrl;
    private String profileType;
    private boolean hasParentalControl;
    private boolean isPrimaryProfile;


}

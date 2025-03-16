package com.movienetscape.accountmanagementservice.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.movienetscape.accountmanagementservice.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MigrateAccountPlanResponse {
    private String message;

    private String previousPlanName;

    private String newPlanName;

    private String accountStatus;

    private boolean hasActiveSubscription;

    private List<Profile> profiles;


}

package com.movienetscape.usermanagementservice.dto.response;


import com.movienetscape.usermanagementservice.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private String userId;

    private String currentAccountPlanName;

    private String accountStatus;

    private boolean hasActiveSubscription;

    private List<Profile> profiles;


    ;


}

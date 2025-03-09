package com.movienetscape.usermanagementservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrateAccountPlanRequest {

    private String userId;

    private String currentPlanName;

    private String newPlanName;
}

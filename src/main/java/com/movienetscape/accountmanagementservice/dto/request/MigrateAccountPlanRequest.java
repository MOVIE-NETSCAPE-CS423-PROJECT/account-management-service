package com.movienetscape.accountmanagementservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrateAccountPlanRequest {

    private String accountId;

    private String currentPlanName;

    private String newPlanName;
}

package com.movienetscape.usermanagementservice.dto.response;


import com.movienetscape.usermanagementservice.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MigrateAccountPlanResponse {
    private String message;
    private Account account;

}

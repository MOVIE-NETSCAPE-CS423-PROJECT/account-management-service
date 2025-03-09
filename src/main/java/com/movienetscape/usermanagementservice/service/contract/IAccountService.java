package com.movienetscape.usermanagementservice.service.contract;

import com.movienetscape.usermanagementservice.dto.response.AccountResponse;
import com.movienetscape.usermanagementservice.dto.response.MigrateAccountPlanResponse;
import com.movienetscape.usermanagementservice.model.Account;

import java.util.List;


public interface IAccountService {


    public AccountResponse createAccount(String email, String firstName, String lastName);


    public List<Account> getAllAccounts();

    public AccountResponse getAccount(String username);


    public MigrateAccountPlanResponse migrateAccountPlan(String currentPlanName, String newPlanName, String userId);


    public void deleteAccount(String userId);
}

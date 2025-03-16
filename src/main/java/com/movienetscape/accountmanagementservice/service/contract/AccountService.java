package com.movienetscape.accountmanagementservice.service.contract;

import com.movienetscape.accountmanagementservice.dto.request.CreateAccountRequest;
import com.movienetscape.accountmanagementservice.dto.request.ProfileRequest;
import com.movienetscape.accountmanagementservice.dto.response.*;
import com.movienetscape.accountmanagementservice.model.Account;
import com.movienetscape.accountmanagementservice.model.Profile;
import com.movienetscape.accountmanagementservice.util.exception.NotFoundException;

import java.util.List;


public interface AccountService {


    AccountCreationResponse createAccount(CreateAccountRequest createAccountRequest);


    GetAllAccountResponse getAllAccounts();

    AccountResponse getAccount(String username);


    MigrateAccountPlanResponse migrateAccountPlan(String currentPlanName, String newPlanName, String userId);


    List<ProfileResponse> getAccountProfiles(String accountId);

    SubscriptionActivationResponse updateAccountSubscriptionStatus(boolean activated, String accountId);

    ProfileResponse updateAccountProfile(String accountId, String profileId, ProfileRequest profileRequest);


    void deleteAccount(String accountId);

    void removeProfile(String accountId, String profileId);


    void activateAccount(String userId, boolean verified);

    ProfileResponse addProfile(String accountId, ProfileRequest profileRequest);

}

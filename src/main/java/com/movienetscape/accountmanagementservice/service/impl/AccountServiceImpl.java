package com.movienetscape.accountmanagementservice.service.impl;

import com.movienetscape.accountmanagementservice.dto.request.CreateAccountRequest;
import com.movienetscape.accountmanagementservice.dto.request.Plan;
import com.movienetscape.accountmanagementservice.dto.request.ProfileRequest;
import com.movienetscape.accountmanagementservice.dto.response.*;
import com.movienetscape.accountmanagementservice.model.Account;
import com.movienetscape.accountmanagementservice.model.Profile;
import com.movienetscape.accountmanagementservice.repository.AccountRepository;
import com.movienetscape.accountmanagementservice.service.contract.AccountService;
import com.movienetscape.accountmanagementservice.util.AccountStatus;
import com.movienetscape.accountmanagementservice.util.exception.DuplicateException;
import com.movienetscape.accountmanagementservice.util.exception.IllegalOperationException;
import com.movienetscape.accountmanagementservice.util.exception.NotFoundException;
import com.movienetscape.accountmanagementservice.util.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    @Override
    public AccountCreationResponse createAccount(CreateAccountRequest createAccountRequest) {
        if (accountRepository.findByUserId(createAccountRequest.getUserId()).isPresent()) {
            throw new DuplicateException("Account with this email already exists.");
        }

        Plan plan = createAccountRequest.getPlan();
        Account newAccount = new Account();
        newAccount.setUserId(createAccountRequest.getUserId());
        newAccount.initializeAccount(plan.getPlanName(),
                plan.getMaxMoviesPerProfileOnActiveSubscription(),
                plan.getMaxMoviesPerProfileOnNotActiveSubscription());

        accountRepository.save(newAccount);
        return mapToResponse(newAccount);
    }

    @Override
    public GetAllAccountResponse getAllAccounts() {
        return GetAllAccountResponse
                .builder()
                .accounts(accountRepository.findAll()
                        .stream()
                        .map(this::mapAccountToResponse).toList()).build();
    }

    @Override
    public AccountResponse getAccount(String username) {
        Account account = accountRepository.findByUserId(username)
                .orElseThrow(() -> new NotFoundException("no account with this email exists"));

        return mapAccountToResponse(account);
    }


    @Override
    public SubscriptionActivationResponse updateAccountSubscriptionStatus(boolean activated, String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Invalid account id"));

        account.updateSubscriptionStatus(activated);

        return SubscriptionActivationResponse.builder().message("Subscription status activated accordingly").build();
    }

    @Override
    public MigrateAccountPlanResponse migrateAccountPlan(String currentPlanName, String newPlanName, String accountId) {
        if (currentPlanName.equalsIgnoreCase(newPlanName)) {
            throw new BadRequestException("currentPlanName cannot be the same as newPlanName");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account does not exist"));

        Account migratedAccount = account.migrateAccountPlan(newPlanName, currentPlanName);
        accountRepository.save(migratedAccount);

        return MigrateAccountPlanResponse.builder()
                .profiles(migratedAccount.getProfiles())
                .previousPlanName(currentPlanName)
                .newPlanName(newPlanName)
                .accountStatus(AccountStatus.ACTIVE.name())
                .message("Successfully migrated account")
                .build();
    }

    @Override
    public List<ProfileResponse> getAccountProfiles(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException("Account does not exist"));

        return account.getProfiles().stream().map((profile -> ProfileResponse.builder()
                .profileDescription(profile.getProfileDescription())
                .hasParentalControl(profile.isHasParentalControl())
                .profileName(profile.getProfileName())
                .profileImageUrl(profile.getProfileImageUrl())
                .profileType(profile.getProfileType())
                .isPrimaryProfile(profile.isPrimaryProfile())
                .profileId(profile.getId())
                .build())).toList();
    }


    @Override
    public ProfileResponse updateAccountProfile(String accountId, String profileId, ProfileRequest profileRequest) {
        Account account = getDbAccount(accountId);

        Profile accountProfile = account.getProfiles().stream()
                .filter(profile -> profile.getId().equals(profileId))
                .findFirst().orElseThrow(() -> new NotFoundException("Profile does not exist"));
        accountProfile.setProfileName(profileRequest.getProfileName());
        accountProfile.setHasParentalControl(profileRequest.isHasParentalControl());
        accountProfile.setProfileDescription(profileRequest.getProfileDescription());
        accountProfile.setHasParentalControl(profileRequest.isHasParentalControl());
        accountProfile.setProfileImageUrl(profileRequest.getProfileImageUrl());
        accountProfile.setAccount(account);
        accountRepository.save(account);

        return ProfileResponse.builder()
                .profileId(accountProfile.getId())
                .profileName(accountProfile.getProfileName())
                .profileDescription(accountProfile.getProfileDescription())
                .profileImageUrl(accountProfile.getProfileImageUrl())
                .profileType(accountProfile.getProfileType())
                .isPrimaryProfile(accountProfile.isPrimaryProfile())
                .hasParentalControl(accountProfile.isHasParentalControl())
                .build();
    }

    @Override
    public void deleteAccount(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account does not exist"));

        accountRepository.delete(account);
    }

    public ProfileResponse addProfile(String accountId, ProfileRequest profileRequest) {
        Account account = getDbAccount(accountId);

        if (!account.canAddMoreProfile())
            throw new IllegalOperationException("Maximum number of profiles already reached.");
        Profile profile = Profile.builder()
                .isPrimaryProfile(profileRequest.isPrimaryProfile())
                .profileDescription(profileRequest.getProfileDescription())
                .profileName(profileRequest.getProfileName())
                .profileType(profileRequest.getProfileType())
                .hasParentalControl(profileRequest.isHasParentalControl())
                .build();
        account.addProfile(profile);
        accountRepository.save(account);
        return ProfileResponse.builder()
                .profileImageUrl(profile.getProfileImageUrl())
                .hasParentalControl(profileRequest.isHasParentalControl())
                .profileDescription(profileRequest.getProfileDescription())
                .profileName(profileRequest.getProfileName())
                .profileType(profileRequest.getProfileType())
                .isPrimaryProfile(profileRequest.isPrimaryProfile())
                .profileId(profile.getId())
                .build();
    }


    public Account getDbAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
    }


    public void removeProfile(String accountId, String profileId) {
        Account account = getDbAccount(accountId);
        if (!account.canRemoveProfile()) throw new IllegalOperationException("Account cant have zero Profile");

        Profile profileToRemove = account.getProfiles().stream()
                .filter(profile -> profile.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Profile with id " + profileId + " not found"));

        account.removeProfile(profileToRemove);

        accountRepository.save(account);

    }

    @Override
    public void activateAccount(String userId, boolean verified) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        account.setVerified(verified);
        accountRepository.save(account);
    }


    private AccountResponse mapAccountToResponse(Account account) {
        return AccountResponse.builder()
                .currentAccountPlanName(account.getPlanName())
                .accountId(account.getId())
                .hasActiveSubscription(account.isHasActiveSubscription())
                .profiles(account.getProfiles())
                .accountStatus(AccountStatus.ACTIVE.name())
                .build();
    }

    private AccountCreationResponse mapToResponse(Account account) {
        return AccountCreationResponse.builder()
                .accountId(account.getId())
                .message("Account successfully created")
                .build();
    }
}

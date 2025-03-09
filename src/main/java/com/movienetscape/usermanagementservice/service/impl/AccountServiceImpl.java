package com.movienetscape.usermanagementservice.service.impl;

import com.movienetscape.usermanagementservice.dto.response.AccountResponse;
import com.movienetscape.usermanagementservice.dto.response.MigrateAccountPlanResponse;
import com.movienetscape.usermanagementservice.model.Account;
import com.movienetscape.usermanagementservice.repository.AccountRepository;
import com.movienetscape.usermanagementservice.service.contract.IAccountService;
import com.movienetscape.usermanagementservice.util.AccountStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(String email, String firstName, String lastName) {

        accountRepository.findByUserId(email).ifPresent(account -> {
            throw new RuntimeException("Account already exists");
        });
        Account newAccount = new Account();
        newAccount.initializeAccount("BASIC".toLowerCase());
        accountRepository.save(newAccount);
        return AccountResponse
                .builder()
                .currentAccountPlanName(newAccount.getCurrentPlanName())
                .userId(newAccount.getUserId())
                .hasActiveSubscription(newAccount.isHasActiveSubscription())
                .profiles(newAccount.getProfiles())
                .accountStatus(AccountStatus.ACTIVE.name())
                .build();

    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountResponse getAccount(String username) {
        Account account = accountRepository.findByUserId(username).orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountResponse
                .builder()
                .currentAccountPlanName(account.getCurrentPlanName())
                .userId(account.getUserId())
                .hasActiveSubscription(account.isHasActiveSubscription())
                .profiles(account.getProfiles())
                .accountStatus(AccountStatus.ACTIVE.name())
                .build();


    }

    @Override
    public MigrateAccountPlanResponse migrateAccountPlan(String currentPlanName, String newPlanName, String userId) {
        if (currentPlanName.matches(newPlanName)) {
        }
        accountRepository.findByUserId(userId).ifPresent(account -> {
            account.migrateAccountPlan(newPlanName);
        })
        return accountRepository.save()
    }

    @Override
    public void deleteAccount(String userId) {
        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.delete(account);
    }
}

package com.movienetscape.accountmanagementservice.controller;


import com.movienetscape.accountmanagementservice.dto.request.ProfileRequest;
import com.movienetscape.accountmanagementservice.dto.request.CreateAccountRequest;
import com.movienetscape.accountmanagementservice.dto.request.MigrateAccountPlanRequest;
import com.movienetscape.accountmanagementservice.dto.response.*;
import com.movienetscape.accountmanagementservice.service.contract.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountCreationResponse> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(request));
    }

    @GetMapping
    public ResponseEntity<GetAllAccountResponse> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String username) {
        return ResponseEntity.ok(accountService.getAccount(username));
    }


    @PostMapping("/{accountId}/profiles")
    public ResponseEntity<ProfileResponse> addProfile(@PathVariable String accountId, @RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(accountService.addProfile(accountId, profileRequest));
    }


    @DeleteMapping("/{accountId}/profiles/{profileId}")
    public ResponseEntity<Void> removeProfile(@PathVariable String accountId, @PathVariable String profileId) {
        accountService.removeProfile(accountId, profileId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/migrate")
    public ResponseEntity<MigrateAccountPlanResponse> migrateAccountPlan(@RequestBody MigrateAccountPlanRequest request) {
        return ResponseEntity.ok(accountService.migrateAccountPlan(request.getCurrentPlanName(), request.getNewPlanName(), request.getAccountId()));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/profiles")
    public ResponseEntity<List<ProfileResponse>> getAccountProfiles(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.getAccountProfiles(accountId));
    }

    @PutMapping("/{accountId}/profiles/{profileId}")
    public ResponseEntity<ProfileResponse> updateAccountProfile(@PathVariable String accountId, @PathVariable String profileId, @RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(accountService.updateAccountProfile(accountId, profileId, profileRequest));
    }


}

package com.movienetscape.usermanagementservice.controller;


import com.movienetscape.usermanagementservice.dto.request.LinkAccountRequest;
import com.movienetscape.usermanagementservice.dto.request.MigrateAccountPlanRequest;
import com.movienetscape.usermanagementservice.dto.response.AccountResponse;
import com.movienetscape.usermanagementservice.dto.response.MigrateAccountPlanResponse;
import com.movienetscape.usermanagementservice.model.Account;
import com.movienetscape.usermanagementservice.model.AccountPlan;
import com.movienetscape.usermanagementservice.service.contract.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody LinkAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request.getEmail(), request.getFirstname(), request.getLastname()));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String username) {
        return ResponseEntity.ok(accountService.getAccount(username));
    }

    @PutMapping("/{planName}")
    public ResponseEntity<MigrateAccountPlanResponse> updateAccountPlan(@RequestBody MigrateAccountPlanRequest request) {
        return ResponseEntity.ok(accountService.migrateAccountPlan(request.getCurrentPlanName(), request.getNewPlanName(), request.getUserId()));
    }

    @DeleteMapping("/{planName}")
    public ResponseEntity<Void> deleteAccountPlan(@PathVariable String planName) {
        accountPlanService.deleteAccountPlan(planName);
        return ResponseEntity.noContent().build();
    }
}

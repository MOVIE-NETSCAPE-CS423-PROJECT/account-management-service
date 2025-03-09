package com.movienetscape.usermanagementservice.controller;


import com.movienetscape.usermanagementservice.model.AccountPlan;
import com.movienetscape.usermanagementservice.service.contract.IAccountPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts/account-plans")
public class AccountPlanController {

    private final IAccountPlanService accountPlanService;

    public AccountPlanController(IAccountPlanService accountPlanService) {
        this.accountPlanService = accountPlanService;
    }

    @PostMapping
    public ResponseEntity<AccountPlan> createAccountPlan(@RequestBody AccountPlan accountPlan) {
        return ResponseEntity.ok(accountPlanService.createAccountPlan(accountPlan));
    }

    @GetMapping
    public ResponseEntity<List<AccountPlan>> getAllAccounts() {
        return ResponseEntity.ok(accountPlanService.getAllAccountPlans());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountPlan> getAccount(@PathVariable String username) {
        Optional<AccountPlan> plan = accountPlanService.getAccountPlanByName(username);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{planName}")
    public ResponseEntity<Account> migrateAccountPlan(@PathVariable String planName, @RequestBody AccountPlan accountPlan) {
        return ResponseEntity.ok(accountPlanService.updateAccountPlan(planName, accountPlan));
    }

    @DeleteMapping("/{planName}")
    public ResponseEntity<Void> deleteAccountPlan(@PathVariable String planName) {
        accountPlanService.deleteAccountPlan(planName);
        return ResponseEntity.noContent().build();
    }
}


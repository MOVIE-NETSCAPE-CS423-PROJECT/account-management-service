package com.movienetscape.usermanagementservice.service.impl;

import com.movienetscape.usermanagementservice.model.AccountPlan;
import com.movienetscape.usermanagementservice.repository.AccountPlanRepository;
import com.movienetscape.usermanagementservice.service.contract.IAccountPlanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountPlanServiceImpl implements IAccountPlanService {

    private final AccountPlanRepository accountPlanRepository;

    public AccountPlanServiceImpl(AccountPlanRepository accountPlanRepository) {
        this.accountPlanRepository = accountPlanRepository;
    }


    public AccountPlan createAccountPlan(AccountPlan accountPlan) {
        if (accountPlanRepository.findByPlanName(accountPlan.getPlanName()).isPresent()) {
            throw new IllegalArgumentException("Plan name already exists");
        }
        return accountPlanRepository.save(accountPlan);
    }


    public List<AccountPlan> getAllAccountPlans() {
        return accountPlanRepository.findAll();
    }



    public Optional<AccountPlan> getAccountPlanByName(String planName) {
        return accountPlanRepository.findByPlanName(planName);
    }


    public AccountPlan updateAccountPlan(String planName, AccountPlan updatedPlan) {
        return accountPlanRepository.findByPlanName(planName).map(plan -> {
            plan.setPlanName(updatedPlan.getPlanName());
            plan.setPlanDescription(updatedPlan.getPlanDescription());
            plan.setBenefits(updatedPlan.getBenefits());
            return accountPlanRepository.save(plan);
        }).orElseThrow(() -> new RuntimeException("Plan not found"));
    }


    public void deleteAccountPlan(String planName) {

        if (accountPlanRepository.existByPlanName(planName).isEmpty()) {
            throw new RuntimeException("Plan not found");
        }
        accountPlanRepository.deleteByPlanName(planName);
    }
}

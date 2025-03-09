package com.movienetscape.usermanagementservice.service.contract;


import com.movienetscape.usermanagementservice.model.AccountPlan;
import com.movienetscape.usermanagementservice.repository.AccountPlanRepository;

import java.util.List;
import java.util.Optional;

public interface IAccountPlanService {


    public AccountPlan createAccountPlan(AccountPlan accountPlan);


    public List<AccountPlan> getAllAccountPlans();



    public Optional<AccountPlan> getAccountPlanByName(String planName);

    public AccountPlan updateAccountPlan(String planName, AccountPlan updatedPlan);

    public void deleteAccountPlan(String planName);
}

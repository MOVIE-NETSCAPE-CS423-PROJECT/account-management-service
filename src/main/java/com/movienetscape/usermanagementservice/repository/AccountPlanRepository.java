package com.movienetscape.usermanagementservice.repository;

import com.movienetscape.usermanagementservice.model.AccountPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountPlanRepository extends JpaRepository<AccountPlan, Long> {

    Optional<AccountPlan> findByPlanName(String planName);

    Optional<Boolean> existByPlanName(String planName);

    void deleteByPlanName(String planName);


}

package com.movienetscape.accountmanagementservice.repository;

import com.movienetscape.accountmanagementservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUserId(String userId);

}

package com.movienetscape.usermanagementservice.repository;

import com.movienetscape.usermanagementservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByUserId(String userId);

}

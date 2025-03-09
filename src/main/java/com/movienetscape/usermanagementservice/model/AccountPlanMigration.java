package com.movienetscape.usermanagementservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlanMigration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountPlanMigrationId;

    @Column(nullable = false)
    private LocalDate migrationDate;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account userAccount;


    @OneToOne
    @JoinColumn(name = "from_plan_id", nullable = false)
    private AccountPlan fromAccountPlan;

    @OneToOne
    @JoinColumn(name = "to_plan_id", nullable = false)
    private AccountPlan toAccountPlan;

}

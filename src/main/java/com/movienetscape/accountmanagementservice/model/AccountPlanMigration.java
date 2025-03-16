package com.movienetscape.accountmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPlanMigration extends BaseEntity {


    @Column(nullable = false)
    private LocalDate migrationDate;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account userAccount;

    @Column(name = "from_plan_name", nullable = false)
    private String fromPlanName;

    @Column(name = "to_plan_name", nullable = false)
    private String toPlanName;

    @Override
    public String generateId() {
        return "apm-" + UUID.randomUUID().toString().substring(0, 7);
    }
}

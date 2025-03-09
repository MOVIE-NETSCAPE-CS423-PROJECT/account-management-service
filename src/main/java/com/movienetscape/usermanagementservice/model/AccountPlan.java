package com.movienetscape.usermanagementservice.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_plans")
public class AccountPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(unique = true, nullable = false)
    private String planName;

    @Column
    private String planDescription;

    @ElementCollection
    @CollectionTable(name = "account_plan_benefits", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "benefit")
    private List<String> benefits;
}


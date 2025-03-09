package com.movienetscape.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "accounts", indexes = @Index(name = "idx_user_id", columnList = "userId"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(name = "current_plan_id")
    private String currentPlanName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @Column(nullable = false)
    private boolean hasActiveSubscription;

    public void initializeAccount(String planName) {
        this.active = true;
        this.currentPlanName = planName;
        this.hasActiveSubscription = false;

        if (this.profiles.isEmpty()) {
            Profile defaultProfile = new Profile();
            defaultProfile.setAccount(this);
            this.profiles.add(defaultProfile);
        }
    }

    public void deactivateAccount() {
        this.active = false;
    }

    public void migrateAccountPlan(String name) {
        profiles.forEach(Profile::clearWatchList);
        this.currentPlanName = name;
        this.hasActiveSubscription = false;
    }

    public void updateSubscriptionStatus(boolean isActive) {
        this.hasActiveSubscription = isActive;
    }

    public boolean checkActiveSubscription() {
        return this.hasActiveSubscription;
    }

    public boolean addProfile(Profile profile) {
        if (this.profiles.size() < 2) {
            this.profiles.add(profile);
            profile.setAccount(this);
            return true;
        }
        return false;
    }


}

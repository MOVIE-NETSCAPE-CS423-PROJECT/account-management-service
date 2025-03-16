package com.movienetscape.accountmanagementservice.model;

import com.movienetscape.accountmanagementservice.dto.request.Plan;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts", indexes = @Index(name = "idx_user_id", columnList = "userId"))
public class Account extends BaseEntity {

    @Column(nullable = false)
    private boolean isVerified;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String planName;
    @Column(nullable = false)
    private int maxMoviesPerProfileOnActiveSubscription;
    @Column(nullable = false)
    private int maxMoviesPerProfileOnNotActiveSubscription;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountPlanMigration> accountPlanMigrations = new ArrayList<>();

    @Column(nullable = false)
    private boolean hasActiveSubscription;

    @Column(nullable = false)
    private int maxWatchListSize;

    @Override
    public String generateId() {
        return "mns-act-" + UUID.randomUUID().toString().substring(0, 7);
    }

    public void initializeAccount(String planName, int maxMoviesPerProfileOnActiveSubscription, int maxMoviesPerProfileOnNotActiveSubscription) {
        this.isVerified = false;
        this.planName = planName;
        this.maxMoviesPerProfileOnActiveSubscription = maxMoviesPerProfileOnActiveSubscription;
        this.maxMoviesPerProfileOnNotActiveSubscription = maxMoviesPerProfileOnNotActiveSubscription;
        this.hasActiveSubscription = false;

        if (profiles.isEmpty()) {
            Profile defaultProfile = new Profile();
            defaultProfile.setHasParentalControl(false);
            defaultProfile.setProfileName("Default");
            defaultProfile.setPrimaryProfile(true);
            defaultProfile.setAccount(this);
            profiles.add(defaultProfile);
        }

        setMaxWatchListSizeForProfiles();
    }


    private int calculateMaxMoviesPerProfile() {
        int totalMaxMoviesForAccount = switch (planName.toUpperCase()) {
            case "BASIC", "GOLD", "PREMIUM" ->
                    hasActiveSubscription ? maxMoviesPerProfileOnActiveSubscription : maxMoviesPerProfileOnNotActiveSubscription;
            default -> 0;
        };


        return !profiles.isEmpty() ? totalMaxMoviesForAccount / profiles.size() : 0;
    }

    private void setMaxWatchListSizeForProfiles() {
        int maxMoviesPerProfile = calculateMaxMoviesPerProfile();
        for (Profile profile : profiles) {
            profile.setMaxMovies(maxMoviesPerProfile);
        }
    }


    public void updateSubscriptionStatus(boolean isActive) {

        this.hasActiveSubscription = isActive;
        setMaxWatchListSizeForProfiles();
    }

    public boolean canAddMoreProfile() {
        return profiles.size() < 2;
    }

    public boolean canRemoveProfile() {
        return profiles.size() == 2;
    }

    public void removeProfile(Profile profile) {
        profiles.remove(profile);

    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
        profile.setAccount(this);
        setMaxWatchListSizeForProfiles();

    }


    public Account migrateAccountPlan(String toPlanName, String fromPlanName) {
        AccountPlanMigration migration = AccountPlanMigration.builder()
                .fromPlanName(fromPlanName)
                .toPlanName(toPlanName)
                .migrationDate(LocalDate.now())
                .userAccount(this)
                .build();

        accountPlanMigrations.add(migration);
        profiles.forEach(Profile::clearWatchList);
        this.planName = toPlanName;
        this.hasActiveSubscription = false;
        setMaxWatchListSizeForProfiles();
        return this;
    }
}

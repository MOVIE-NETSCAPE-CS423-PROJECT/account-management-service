package com.movienetscape.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private WatchList watchList;

    @Column(nullable = false)
    private boolean hasParentalControl;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    public void initializeWatchList() {
        if (this.watchList == null) {
            this.watchList = new WatchList();
            this.watchList.setProfile(this);
        }
    }


    public void toggleParentalControl() {
        this.hasParentalControl = !this.hasParentalControl;
    }

    public void clearWatchList() {
        watchList.clearWatchList();
    }


}


package com.movienetscape.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WatchListMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "watch_list_id", nullable = false)
    private WatchList watchList;

    @Column(nullable = false)
    private boolean hasStartedWatching = false;

    @Column(nullable = false)
    private Long movieId;

    public void startWatching() {
        this.hasStartedWatching = true;
    }
}

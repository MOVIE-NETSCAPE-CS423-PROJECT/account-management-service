package com.movienetscape.accountmanagementservice.repository;

import com.movienetscape.accountmanagementservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    @Query("""
        SELECT p
        FROM Profile p
        INNER JOIN FETCH p.favouriteMovies f
        WHERE p.id = :profileId
    """)
    Optional<Profile> findProfileWithFavouriteMovies(@Param("profileId") String profileId);

    @Query("""
                SELECT p
                FROM Profile p
                INNER JOIN FETCH p.boughtMovies b
                WHERE p.id = :profileId
            """)
    Optional<Profile> findProfileWithBoughtMovies(@Param("profileId") String profileId);

    @Query("""
        SELECT p
        FROM Profile p
        INNER JOIN FETCH p.watchListMovies w
        WHERE p.id = :profileId
    """)
    Optional<Profile> findProfileWithWatchListMovies(@Param("profileId") String profileId);
}



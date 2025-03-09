package com.movienetscape.usermanagementservice.repository;

import com.movienetscape.usermanagementservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

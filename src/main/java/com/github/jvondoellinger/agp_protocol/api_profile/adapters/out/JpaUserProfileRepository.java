package com.github.jvondoellinger.agp_protocol.api_profile.adapters.out;

import com.github.jvondoellinger.agp_protocol.api_profile.infrastructure.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}

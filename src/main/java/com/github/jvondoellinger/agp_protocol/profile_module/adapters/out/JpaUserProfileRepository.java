package com.github.jvondoellinger.agp_protocol.profile_module.adapters.out;

import com.github.jvondoellinger.agp_protocol.profile_module.infrastructure.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}

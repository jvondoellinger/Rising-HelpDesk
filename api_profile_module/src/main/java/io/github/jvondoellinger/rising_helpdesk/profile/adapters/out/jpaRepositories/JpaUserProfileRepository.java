package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}

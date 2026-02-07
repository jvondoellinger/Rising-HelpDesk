package io.github.jvondoellinger.rising_helpdesk.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.infrastructure.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}

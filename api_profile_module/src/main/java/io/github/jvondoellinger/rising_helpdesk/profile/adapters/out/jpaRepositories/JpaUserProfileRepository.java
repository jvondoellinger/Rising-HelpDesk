package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}

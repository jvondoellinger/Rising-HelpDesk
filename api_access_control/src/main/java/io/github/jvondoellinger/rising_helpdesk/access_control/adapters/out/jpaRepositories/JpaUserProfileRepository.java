package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.access_control.adapters.out.entities.UserProfileDbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, UUID> {
	Page<UserProfileDbEntity> findByAccessProfileId(UUID accessProfileId, Pageable pageable);
}

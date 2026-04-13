package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.AccessProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAccessProfileRepository extends JpaRepository<AccessProfileDbEntity, UUID> {
	boolean existsByName(String name);

	Optional<AccessProfileDbEntity> findByName(String name);
}

package io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccessProfileRepository extends CrudRepository<AccessProfile, UUID> {
	boolean existsByName(String name);

	Optional<AccessProfile> findByName(String name);
}

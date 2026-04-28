package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.kernel.infrastructure.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccessProfileRepository extends CrudRepository<AccessProfile, UUID> {
	boolean existsByName(String name);

	Optional<AccessProfile> findByName(String name);
}

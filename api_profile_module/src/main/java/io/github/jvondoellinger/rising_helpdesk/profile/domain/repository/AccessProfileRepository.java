package io.github.jvondoellinger.rising_helpdesk.profile.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;

import java.util.UUID;

public interface AccessProfileRepository extends CrudRepository<AccessProfile, UUID> {
	boolean existsByName(String name);
}

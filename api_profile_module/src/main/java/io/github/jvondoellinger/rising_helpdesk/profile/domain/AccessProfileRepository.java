package io.github.jvondoellinger.rising_helpdesk.profile.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

public interface AccessProfileRepository extends CrudsRepository<AccessProfile> {
	boolean existsByName(String name);
}

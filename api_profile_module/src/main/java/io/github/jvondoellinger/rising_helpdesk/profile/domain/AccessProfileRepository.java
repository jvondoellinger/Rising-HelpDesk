package io.github.jvondoellinger.rising_helpdesk.profile.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

public interface AccessProfileRepository extends CrudsRepository<AccessProfile, AccessProfileId> {
	boolean existsByName(String name);
}

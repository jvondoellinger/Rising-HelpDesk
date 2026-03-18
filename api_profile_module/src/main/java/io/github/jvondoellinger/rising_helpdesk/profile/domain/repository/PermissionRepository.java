package io.github.jvondoellinger.rising_helpdesk.profile.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends CrudsRepository<Permission, UUID> {
	Optional<Permission> findByCode(String code);
}

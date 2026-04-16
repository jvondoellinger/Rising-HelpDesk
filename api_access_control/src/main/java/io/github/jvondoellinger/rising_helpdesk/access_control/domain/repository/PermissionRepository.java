package io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends CrudRepository<Permission, UUID> {
	Optional<Permission> findByCode(String code);
}

package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends CrudRepository<Permission, UUID> {
	Optional<Permission> findByCode(String code);

	boolean existsByCode(String code);
	boolean allExistsByCode(List<String> codes);
	boolean batchExistsById(List<UUID> ids);

}

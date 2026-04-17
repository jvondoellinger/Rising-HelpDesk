package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.entities.PermissionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionDbEntity, UUID> {
	Optional<PermissionDbEntity> findByCode(String code);
}

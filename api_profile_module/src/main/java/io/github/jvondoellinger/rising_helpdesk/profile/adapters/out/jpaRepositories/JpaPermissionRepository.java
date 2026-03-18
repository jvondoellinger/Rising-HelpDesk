package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.PermissionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionDbEntity, UUID> {
}

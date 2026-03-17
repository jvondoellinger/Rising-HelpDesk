package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories.JpaPermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.PermissionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;

    @Override
    public Permission save(Permission entity) {
        var dbEntity = new PermissionDbEntity();

        dbEntity.setPermission(entity.getCode());
        dbEntity.setCreatedAt(dbEntity.getCreatedAt());

        jpaPermissionRepository.save(dbEntity);

        return entity;
    }

    @Override
    public Permission update(Permission entity) {
        return null;
    }

    @Override
    public void delete(Permission entity) {

    }

    @Override
    public Permission queryById(UUID uuid) {
        return null;
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Pagination<Permission> query(QueryFilter filter) {
        return null;
    }

    @Override
    public long total() {
        return 0;
    }
}

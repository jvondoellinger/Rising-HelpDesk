package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories.JpaPermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.PermissionDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;
    private final PermissionDbMapper dbMapper;

    @Override
    public Permission save(Permission entity) {
        var dbEntity = dbMapper.from(entity);
        var persisted = jpaPermissionRepository.save(dbEntity);

        return dbMapper.toPermission(persisted);
    }

    @Override
    public Permission update(Permission entity) {
        var current = jpaPermissionRepository.findById(entity.getId());

        if (current.isEmpty()) {
            return null;
        }

        var mapped = dbMapper.from(entity);
        var updated = jpaPermissionRepository.save(mapped);

        return dbMapper.toPermission(updated);
    }

    @Override
    public void delete(Permission entity) {
        jpaPermissionRepository.deleteById(entity.getId());
    }

    @Override
    public Permission queryById(UUID uuid) {
        var dbEntity = jpaPermissionRepository.findById(uuid);

        if (dbEntity.isEmpty()) {
            return null;
        }

        return dbMapper.toPermission(dbEntity.get());
    }

    @Override
    public boolean existsById(UUID uuid) {
        return jpaPermissionRepository.existsById(uuid);
    }

    @Override
    public Pagination<Permission> query(PaginationFilter filter) {
        var pageable = PageRequest.of(filter.page(), filter.size());
        var entities = jpaPermissionRepository.findAll(pageable);
        var permissionsList = entities.stream().map(dbMapper::toPermission).toList();

        return new Pagination<>(permissionsList, entities.getNumber(), entities.getSize(), entities.getTotalPages());
    }

    @Override
    public long total() {
        return jpaPermissionRepository.count();
    }
}

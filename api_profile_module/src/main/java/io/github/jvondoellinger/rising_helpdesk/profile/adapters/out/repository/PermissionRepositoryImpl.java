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

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;
    private final PermissionDbMapper dbMapper;

    @Override
    public void save(Permission entity) {
        var dbEntity = dbMapper.from(entity);

        jpaPermissionRepository.save(dbEntity);
    }

    @Override
    public void update(Permission entity) {
        var current = jpaPermissionRepository.findById(entity.getId());

        if (current.isEmpty()) {
            return;
        }

        var mapped = dbMapper.from(entity);

        jpaPermissionRepository.save(mapped);
    }

    @Override
    public void delete(Permission entity) {
        jpaPermissionRepository.deleteById(entity.getId());
    }

    @Override
    public Optional<Permission> findById(UUID uuid) {
        var optional = jpaPermissionRepository.findById(uuid);

        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var permission = optional.get();

        return Optional.of(dbMapper.toPermission(permission));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return jpaPermissionRepository.existsById(uuid);
    }

    @Override
    public Pagination<Permission> findByPagination(PaginationFilter filter) {
        var pageable = PageRequest.of(filter.page(), filter.size());
        var entities = jpaPermissionRepository.findAll(pageable);
        var permissionsList = entities.stream()
                .map(dbMapper::toPermission)
                .toList();

        return Pagination.of(permissionsList, entities.getNumber(), entities.getTotalPages());
    }

    @Override
    public long total() {
        return jpaPermissionRepository.count();
    }

    @Override
    public Optional<Permission> findByCode(String code) {
        var optional = jpaPermissionRepository.findByCode(code);

        if (optional.isEmpty()) {
            return Optional.empty();
        }

        var entity = optional.get();
        return Optional.of(dbMapper.toPermission(entity));
    }
}

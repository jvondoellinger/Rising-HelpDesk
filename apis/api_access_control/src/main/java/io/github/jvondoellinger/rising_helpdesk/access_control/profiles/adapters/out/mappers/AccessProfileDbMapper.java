package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.entities.AccessProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.entities.PermissionDbEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileDbMapper {
    public AccessProfileDbEntity from(AccessProfile accessProfile) {
        var accessProfileDbEntity = new AccessProfileDbEntity();

        accessProfileDbEntity.setId(accessProfile.getId());
        accessProfileDbEntity.setName(accessProfile.getName());
        accessProfileDbEntity.setCreatedAt(accessProfile.getCreatedAt());
        accessProfileDbEntity.setUpdatedAt(accessProfile.getUpdatedAt());

        for (var permission : accessProfile.getPermissions()) {
            var permissionDbEntity = new PermissionDbEntity();

            permissionDbEntity.setId(permission.getId());
            permissionDbEntity.setCreatedAt(permission.getCreatedAt());

            accessProfileDbEntity.addPermission(permissionDbEntity);
        }

        return accessProfileDbEntity;
    }

    public AccessProfile toAccessProfile(AccessProfileDbEntity accessProfileDbEntity) {
        var domainPermissions = accessProfileDbEntity
                .getPermissions()
                .stream()
                .map(permissionDbEntity ->
                        new Permission(permissionDbEntity.getId(), permissionDbEntity.getCode(), permissionDbEntity.getCreatedAt())
                )
                .toList();

        return new AccessProfile(
                accessProfileDbEntity.getId(),
                accessProfileDbEntity.getName(),
                domainPermissions,
                accessProfileDbEntity.getCreatedAt(),
                accessProfileDbEntity.getUpdatedAt()
        );
    }
}

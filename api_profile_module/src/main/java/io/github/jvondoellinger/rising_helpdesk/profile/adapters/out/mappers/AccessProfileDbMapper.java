package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.AccessProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileDbMapper {
	public AccessProfileDbEntity from(AccessProfile accessProfile) {
		return new AccessProfileDbEntity(
			   accessProfile.getId().toString(),
			   accessProfile.getName(),
			   accessProfile.getPermissions(),
			   accessProfile.getCreatedAt(),
			   accessProfile.getUpdatedAt()
		);
	}

	public AccessProfile toAccessProfile(AccessProfileDbEntity accessProfileDbEntity) {
		return new AccessProfile(
			   AccessProfileId.of(accessProfileDbEntity.getDomainId()),
			   accessProfileDbEntity.getName(),
			   accessProfileDbEntity.getPermissions(),
			   accessProfileDbEntity.getCreatedAt(),
			   accessProfileDbEntity.getUpdatedAt()
		);
	}
}

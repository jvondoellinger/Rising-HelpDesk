package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.UserProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDbMapper {
	public UserProfileDbEntity from(UserProfile userProfile) {
		return new UserProfileDbEntity(userProfile);
	}

	public UserProfile toUserProfile(UserProfileDbEntity userProfileDbEntity) {
		return new UserProfile(
			   UserProfileId.of(userProfileDbEntity.getUserId()),
			   userProfileDbEntity.getAccessProfile(),
			   userProfileDbEntity.getCreatedAt(),
			   userProfileDbEntity.getUpdatedAt()
		);
	}
}

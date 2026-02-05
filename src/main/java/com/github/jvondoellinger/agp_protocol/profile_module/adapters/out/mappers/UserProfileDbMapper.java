package com.github.jvondoellinger.agp_protocol.profile_module.adapters.out.mappers;

import com.github.jvondoellinger.agp_protocol.profile_module.domain.UserProfile;
import com.github.jvondoellinger.agp_protocol.profile_module.infrastructure.UserProfileDbEntity;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
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

package com.github.jvondoellinger.agp_protocol.api_profile.adapters.out.mappers;

import com.github.jvondoellinger.agp_protocol.api_profile.domain.UserProfile;
import com.github.jvondoellinger.agp_protocol.api_profile.infrastructure.UserProfileDbEntity;
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

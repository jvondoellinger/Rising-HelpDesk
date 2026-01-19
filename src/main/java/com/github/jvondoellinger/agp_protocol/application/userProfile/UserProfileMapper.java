package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper;
import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import org.springframework.stereotype.Service;

import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.mapAccessProfileIdDTO;
import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.mapUserProfileIdDTO;

@Service
public class UserProfileMapper {

	public UserProfile from(CreateUserProfileRequestDTO requestDTO) {
		var accessProfileIdStr = requestDTO.accessProfileId().id();
		var accessProfile = accessProfileIdOnly(accessProfileIdStr);
		return new UserProfile(
			accessProfile
		);
	}

	public CreateUserProfileResponseDTO mapToResponse(UserProfile userProfile) {
		var domainDtoId = mapUserProfileIdDTO(userProfile.getUserId().value());
		var accessProfileId = mapAccessProfileIdDTO(userProfile.getAccessProfile().getDomainId().value());

		return new CreateUserProfileResponseDTO(
			domainDtoId,
			   accessProfileId,
			   userProfile.getCreatedAt(),
			   userProfile.getUpdatedAt()
		);
	}


	private AccessProfile accessProfileIdOnly(String id) {
		return new AccessProfile(
			   DomainId.parse(id),
			   null,
			   null,
			   null,
			   null
		);
	}
}

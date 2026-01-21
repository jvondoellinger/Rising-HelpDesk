package com.github.jvondoellinger.agp_protocol.application.shared;

import com.github.jvondoellinger.agp_protocol.application.shared.id.AccessProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;

public class DtoSharedMapper {
	private DtoSharedMapper() {}

	public static QueueIdDTO mapQueueId(DomainId id) {
		return new QueueIdDTO(id.value());
	}
	public static UserProfileIdDTO mapUserProfileIdDTO(String userId) {
		return new UserProfileIdDTO(userId);
	}
	public static AccessProfileIdDTO mapAccessProfileIdDTO(String accessProfileId) {
		return new AccessProfileIdDTO(accessProfileId);
	}
}

package com.github.jvondoellinger.agp_protocol.application.queue.mapper;

import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import org.springframework.stereotype.Service;

import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.mapQueueId;
import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.mapUserProfileIdDTO;

@Service
public class QueueMapper {
	public Queue from(CreateQueueRequestDTO requestDTO) {
		var userProfileIdOnly = userProfileIdOnly(requestDTO.createdBy().id());
		return new Queue(
			   requestDTO.area(),
			   requestDTO.subarea(),
			   userProfileIdOnly
		);
	}

	public CreateQueueResponseDTO toCreateResponse(Queue queue) {
		var id = mapQueueId(queue.getDomainId());
		var createdById = mapUserProfileIdDTO(queue.getCreatedBy().getUserId().value());
		var updatedById = lastUpdatedBy(queue);
		return new CreateQueueResponseDTO(
			   id,
			   queue.getArea(),
			   queue.getSubarea(),
			   createdById,
			   queue.getCreatedAt(),
			   updatedById,
			   queue.getUpdatedAt()
		);
	}

	private UserProfile userProfileIdOnly(String id) {
		return new UserProfile(
			   DomainId.parse(id),
			   null,
			   null,
			   null
		);
	}
	private UserProfileIdDTO lastUpdatedBy(Queue queue) {
		return queue.getLastUpdatedBy() == null ? null : mapUserProfileIdDTO(queue.getLastUpdatedBy().getUserId().value());
	}
}

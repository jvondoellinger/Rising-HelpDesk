package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.mappers;

import com.github.jvondoellinger.agp_protocol.shared_kernel.InteractionId;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.api_ticket.infrastructure.InteractionDbEntity;
import org.springframework.stereotype.Service;

@Service
public class InteractionDbMapper {
	public InteractionDbEntity from(Interaction interaction) {
		return new InteractionDbEntity(
			   interaction.getId().toString(),
			   interaction.getText(),
			   interaction.isVisible(),
			   interaction.getInteractedBy(),
			   interaction.getInteractedOn()
		);
	}

	public Interaction toInteraction(InteractionDbEntity interactionDbEntity) {
		return new Interaction(
			   InteractionId.of(interactionDbEntity.getDomainId()),
			   interactionDbEntity.getText(),
			   interactionDbEntity.isVisible(),
			   interactionDbEntity.getInteractedBy(),
			   interactionDbEntity.getInteractedOn()
		);
	}
}

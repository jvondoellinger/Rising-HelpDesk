package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.InteractionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
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

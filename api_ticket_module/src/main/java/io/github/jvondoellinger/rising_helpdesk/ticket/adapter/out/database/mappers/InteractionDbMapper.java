package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.TicketDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.InteractionDbEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InteractionDbMapper {
    public InteractionDbEntity from(Interaction interaction) {
        return new InteractionDbEntity(
                interaction.getId(),
                interaction.getText(),
                interaction.isVisible(),
                interaction.getInteractedBy(),
                interaction.getInteractedOn(),
                blankTicket(interaction.getTicketId())
        );
    }

    public Interaction toInteraction(InteractionDbEntity interactionDbEntity) {
        return new Interaction(
                interactionDbEntity.getId(),
                interactionDbEntity.getText(),
                interactionDbEntity.isVisible(),
                interactionDbEntity.getInteractedBy(),
                interactionDbEntity.getInteractedOn(),
                interactionDbEntity.getTicket().getId()
        );
    }

    private TicketDbEntity blankTicket(UUID ticketId) {
        var ticket = new TicketDbEntity();
        ticket.setId(ticketId);
        return ticket;
    }
}

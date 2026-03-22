package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.InteractionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.MentionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.TicketDbEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketDbEntityMapper {
    public TicketDbEntity from(Ticket ticket) {
        var ticketDbEntity = new TicketDbEntity();

        ticketDbEntity.setId(ticket.getId());
        ticketDbEntity.setTitle(ticket.getTitle());
        ticketDbEntity.setNumber(ticket.getNumber().toString());
        ticketDbEntity.setDeadline(ticket.getDeadline());
        ticketDbEntity.setQueueId(ticket.getQueueId().toString());
        ticketDbEntity.setOpenedOn(ticket.getOpenedOn());
        ticketDbEntity.setOpenedBy(ticket.getOpenedBy().toString());
        ticketDbEntity.setLastUpdatedBy(ticket.getLastUpdatedBy() == null ? null : ticket.getLastUpdatedBy().toString());
        ticketDbEntity.setLastUpdatedOn(ticket.getLastUpdatedOn());

        // !! OBS: Não adicionar o Ticket em setTicket, pode trazer bugs/inconsistencias!
        for (var interaction : ticket.getInteractionIds()) {
            var interactionDbEntity = new InteractionDbEntity();

            interactionDbEntity.setId(interaction);
            ticketDbEntity.addInteraction(interactionDbEntity);
        }
        for (var mention : ticket.getMentions()) {
            var mentionDbEntity = new MentionDbEntity();

            mentionDbEntity.setId(mention);
            ticketDbEntity.addMentions(mentionDbEntity);
        }

        return ticketDbEntity;
    }

    public Ticket toTicket(TicketDbEntity dbEntity) {
        var mentionDomain = dbEntity
                .getMentions()
                .stream()
                .map(MentionDbEntity::getId)
                .toList();
        var interactions = dbEntity
                .getInteractions()
                .stream()
                .map(InteractionDbEntity::getId)
                .toList();
        return new Ticket(
                dbEntity.getId(),
                TicketNumber.parse(dbEntity.getNumber()),
                dbEntity.getTitle(),
                interactions,
                UUID.fromString(dbEntity.getQueueId()),
                mentionDomain,
                dbEntity.getDeadline(),
                UUID.fromString(dbEntity.getOpenedBy()),
                dbEntity.getOpenedOn(),
                UUID.fromString(dbEntity.getLastUpdatedBy()),
                dbEntity.getLastUpdatedOn()
        );
    }
}

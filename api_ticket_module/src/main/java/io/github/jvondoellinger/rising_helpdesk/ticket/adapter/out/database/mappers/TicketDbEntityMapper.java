package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Mention;
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
        ticketDbEntity.setHistory(ticket.getHistory());
        ticketDbEntity.setNumber(ticket.getNumber().toString());
        ticketDbEntity.setDeadline(ticket.getDeadline());
        ticketDbEntity.setQueueId(ticket.getQueueId().toString());
        ticketDbEntity.setOpenedOn(ticket.getOpenedOn());
        ticketDbEntity.setOpenedBy(ticket.getOpenedBy().toString());
        ticketDbEntity.setLastUpdatedBy(ticket.getLastUpdatedBy() == null ? null : ticket.getLastUpdatedBy().toString());
        ticketDbEntity.setLastUpdatedOn(ticket.getLastUpdatedOn());

        // OBS: Não adicionar o Ticket em setTicket, pode trazer bugs/inconsistencias!
        for (var mention : ticket.getMentions()) {
            var mentionDbEntity = new MentionDbEntity();
            mentionDbEntity.setMentionedAt(mentionDbEntity.getMentionedAt());
            mentionDbEntity.setUserProfileId(mention.getUserId());

            ticketDbEntity.addMentions(mentionDbEntity);
        }

        return ticketDbEntity;
    }

    public Ticket toTicket(TicketDbEntity dbEntity) {
        var mentionDomain = dbEntity
                .getMentions()
                .stream()
                .map(mentionDbEntity -> new Mention(mentionDbEntity.getId(), mentionDbEntity.getUserProfileId(), mentionDbEntity.getMentionedByUserId(), mentionDbEntity.getMentionedAt()))
                .toList();

        return new Ticket(
                dbEntity.getId(),
                TicketNumber.parse(dbEntity.getNumber()),
                dbEntity.getTitle(),
                dbEntity.getHistory(),
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

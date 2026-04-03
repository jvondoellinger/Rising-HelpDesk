package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.InteractionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.QueueDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state.TicketStateFactory;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.MentionDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.TicketDbEntity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketDbEntityMapper {
	public TicketDbEntity from(Ticket ticket) {
		var ticketDbEntity = new TicketDbEntity();

		ticketDbEntity.setId(ticket.getId());
		ticketDbEntity.setTitle(ticket.getTitle());
		ticketDbEntity.setNumber(ticket.getNumber().toString());
		ticketDbEntity.setDeadline(ticket.getDeadline());
		ticketDbEntity.setOpenedOn(ticket.getOpenedOn());
		ticketDbEntity.setOpenedBy(ticket.getOpenedBy());
		ticketDbEntity.setLastUpdatedBy(ticket.getLastUpdatedBy() == null ? null : ticket.getLastUpdatedBy());
		ticketDbEntity.setLastUpdatedOn(ticket.getLastUpdatedOn());

		// !! OBS: Não adicionar o Ticket em setTicket, pode trazer bugs/inconsistencias!
		var queueDbEntity = new QueueDbEntity();

		queueDbEntity.setId(ticket.getQueue().getId());
		ticketDbEntity.setQueue(queueDbEntity);

		// !! OBS: Não adicionar o Ticket em setTicket, pode trazer bugs/inconsistencias!
		for (var interaction : ticket.getInteraction()) {
			var interactionDbEntity = new InteractionDbEntity();

			interactionDbEntity.setId(interaction.getId());
			ticketDbEntity.addInteraction(interactionDbEntity);
		}
		// !! OBS: Não adicionar o Ticket em setTicket, pode trazer bugs/inconsistencias!
		for (var mention : ticket.getMentions()) {
			var mentionDbEntity = new MentionDbEntity();

			mentionDbEntity.setId(mention.getId());
			ticketDbEntity.addMentions(mentionDbEntity);
		}


		return ticketDbEntity;
	}

	public Ticket toTicket(TicketDbEntity dbEntity) {
		return new Ticket(
			   dbEntity.getId(),
			   TicketNumber.parse(dbEntity.getNumber()),
			   dbEntity.getTitle(),
			   createInteractionList(dbEntity.getInteractions()),
			   createQueue(dbEntity.getQueue()),
			   createMentions(dbEntity.getMentions()),
			   dbEntity.getDeadline(),
			   TicketStateFactory.from(TicketStatus.valueOf(dbEntity.getStatus())),
			   dbEntity.getOpenedBy(),
			   dbEntity.getOpenedOn(),
			   dbEntity.getLastUpdatedBy(),
			   dbEntity.getLastUpdatedOn()
		);
	}

	private List<Interaction> createInteractionList(List<InteractionDbEntity> interactionDbEntities) {
		return interactionDbEntities.stream()
			   .map(interactionDbEntity ->
					 new Interaction(interactionDbEntity.getId(),
						    interactionDbEntity.getText(),
						    interactionDbEntity.isVisible(),
						    interactionDbEntity.getInteractedBy(),
						    interactionDbEntity.getInteractedOn(),
						    interactionDbEntity.getTicket().getId()
					 )
			   )
			   .toList();
	}

	private Queue createQueue(QueueDbEntity dbEntity) {
		return new Queue(
			   dbEntity.getId(),
			   dbEntity.getArea(),
			   dbEntity.getSubarea(),
			   dbEntity.getAuthor(),
			   dbEntity.getCreatedAt(),
			   dbEntity.getUpdatedAt(),
			   dbEntity.getLastUpdatedBy()
		);
	}

	private List<Mention> createMentions(List<MentionDbEntity> dbEntities) {
		return dbEntities.stream()
			   .map(mentionDbEntity ->
					 new Mention(mentionDbEntity.getId(),
						    mentionDbEntity.getMentionedByUserId(),
						    mentionDbEntity.getMentionedByUserId(),
						    mentionDbEntity.getMentionedAt(),
						    mentionDbEntity.getTicket().getId()
					 )
			   )
			   .toList();
	}
}

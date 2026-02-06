package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.mappers;

import com.github.jvondoellinger.agp_protocol.api_ticket.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.valueObjects.TicketNumber;
import com.github.jvondoellinger.agp_protocol.api_ticket.infrastructure.TicketDbEntity;

public class TicketDbEntityMapper {
	public TicketDbEntity from(Ticket ticket) {
		var mentions = ticket.mentions();
		var history = ticket.interactionHistory();

		var number = ticket.number().toString();
		var title = ticket.title();
		var deadline = ticket.deadline();
		var queueId = ticket.queueId();
		var openedOn = ticket.openedOn();
		var openedBy = ticket.openedBy();
		var lastUpdatedOn = ticket.lastUpdatedOn();
		var lastUpdatedBy = ticket.lastUpdatedBy() == null ? null : ticket.lastUpdatedBy();

		return new TicketDbEntity(
			   number,
			   title,
			   history,
			   deadline,
			   queueId,
			   mentions,
			   openedOn,
			   openedBy,
			   lastUpdatedOn,
			   lastUpdatedBy
		);
	}

	public Ticket toTicket(TicketDbEntity dbEntity) {
		var number = TicketNumber.parse(dbEntity.getNumber());
		var title = dbEntity.getTitle();
		var interactions = dbEntity.getHistory();
		var queueId = dbEntity.getQueueId();
		var mentions = dbEntity.getMentions();

		var deadline = dbEntity.getDeadline();
		var openedBy = dbEntity.getOpenedBy();
		var openedOn = dbEntity.getOpenedOn();
		var lastUpdatedBy = dbEntity.getLastUpdatedBy() == null ?
			   null : dbEntity.getLastUpdatedBy();
		var lastUpdatedOn = dbEntity.getLastUpdatedOn();
		return new Ticket(
			   number,
			   title,
			   interactions,
			   queueId,
			   mentions,
			   deadline,
			   openedBy,
			   openedOn,
			   lastUpdatedBy,
			   lastUpdatedOn
		);
	}
}

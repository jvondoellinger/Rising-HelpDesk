package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.mappers;

import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.valueObjects.TicketNumber;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.InteractionDbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.QueueDbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.TicketDbEntity;
import com.github.jvondoellinger.agp_protocol.userProfile_module.infrastructure.UserProfileDbEntity;

import java.util.ArrayList;

public class TicketDbEntityMapper {
	public TicketDbEntity from(Ticket ticket) {
		var mentions = (new ArrayList<>(ticket.mentions().readonlyList()))
			   .stream()
			   .map(UserProfileId::toString)
			   .map(UserProfileDbEntity::foreignKey)
			   .toList();
		var history = (new ArrayList<>(ticket.interactionIds().readonlyList()))
			   .stream()
			   .map(i -> i.getId().value())
			   .map(InteractionDbEntity::foreignKey)
			   .toList();

		var number = ticket.number().toString();
		var title = ticket.title();
		var deadline = ticket.deadline();
		var queue = QueueDbEntity.foreignKey(ticket.queueId().toString());
		var openedOn = ticket.openedOn();
		var openedBy = UserProfileDbEntity.foreignKey(ticket.openedBy().toString());
		var lastUpdatedOn = ticket.lastUpdatedOn();
		var lastUpdatedBy = ticket.lastUpdatedBy() == null ? null : UserProfileDbEntity.foreignKey(ticket.queueId().toString());

		return new TicketDbEntity(
			   number,
			   title,
			   history,
			   deadline,
			   queue,
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
		var interactions = dbEntity.getHistory()
			   .stream()
			   .map(DbEntity::toDomainEntity)
			   .toList();
		var queueId = QueueId.of(dbEntity.getQueue().getDomainId());
		var mentionsEntity = dbEntity.getMentions()
			   .stream()
			   .map(x -> UserProfileId.of(x.getUserId()))
			   .toList();

		var historyEntity = new InteractionsHistory(interactions);
		var mentions = new Mentions(mentionsEntity);
		var deadline = dbEntity.getDeadline();
		var openedBy = UserProfileId.of(dbEntity.getOpenedBy().getUserId());
		var openedOn = dbEntity.getOpenedOn();
		var lastUpdatedBy = dbEntity.getLastUpdatedBy() == null ?
			   null :
			   UserProfileId.of(dbEntity.getLastUpdatedBy().getUserId());
		var lastUpdatedOn = dbEntity.getLastUpdatedOn();
		return new Ticket(
			   number,
			   title,
			   historyEntity,
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

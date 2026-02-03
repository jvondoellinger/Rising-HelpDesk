package com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.userProfile_module.infrastructure.UserProfileDbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.valueObjects.TicketNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
public class TicketDbEntity implements DbEntity<Ticket> {
	@Id
	private String number;

	private String title;

	@Column(name = "interactions")
	@ManyToMany
	private List<InteractionDbEntity> history;

	private LocalDateTime deadline;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private QueueDbEntity queue;

	@ManyToMany
	private List<UserProfileDbEntity> mentions;

	@CreationTimestamp
	private LocalDateTime openedOn;
	@ManyToOne
	private UserProfileDbEntity openedBy;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime lastUpdatedOn;
	@ManyToOne
	private UserProfileDbEntity lastUpdatedBy;

	@PersistenceCreator
	public TicketDbEntity(String number, String title, List<InteractionDbEntity> history, LocalDateTime deadline, QueueDbEntity queue, List<UserProfileDbEntity> mentions, LocalDateTime openedOn, UserProfileDbEntity openedBy, LocalDateTime lastUpdatedOn, UserProfileDbEntity lastUpdatedBy) {
		this.number = number;
		this.title = title;
		this.history = history;
		this.deadline = deadline;
		this.queue = queue;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.openedBy = openedBy;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public TicketDbEntity(Ticket ticket) {
		var mentions = (new ArrayList<>(ticket.mentions().readonlyList()))
			   .stream()
			   .map(u -> u.getUserId().value())
			   .map(UserProfileDbEntity::foreignKey)
			   .toList();
		this.history = (new ArrayList<>(ticket.history().readonlyList()))
			   .stream()
			   .map(i -> i.getId().value())
			   .map(InteractionDbEntity::foreignKey)
			   .toList();
		this.queue = QueueDbEntity.foreignKey(ticket.queue().getDomainId().value());
		this.number = ticket.number().toString();
		this.title = ticket.title();
		this.deadline = ticket.deadline();
		this.mentions = mentions;
		this.openedBy = UserProfileDbEntity.foreignKey(ticket.openedBy().getUserId().value());
		this.openedOn = ticket.openedOn();
		this.lastUpdatedBy = ticket.lastUpdatedBy() == null ? null : UserProfileDbEntity.foreignKey(ticket.queue().getLastUpdatedBy().toString());
		this.lastUpdatedOn = ticket.lastUpdatedOn();
	}

	protected TicketDbEntity() {}

	@Override
	public Ticket toDomainEntity() {
		var mentionsEntity = mentions.stream().map(DbEntity::toDomainEntity).toList();
		var interactions = history.stream().map(DbEntity::toDomainEntity).toList();
		var historyEntity = new InteractionsHistory(interactions);

		return new Ticket(
			   TicketNumber.parse(number),
			   title,
			   historyEntity,
			   queue.toDomainEntity(),
			   new Mentions(mentionsEntity),
			   deadline,
			   openedBy.toDomainEntity(),
			   openedOn,
			   lastUpdatedBy == null ? null : lastUpdatedBy.toDomainEntity(),
			   lastUpdatedOn
		);
	}
}

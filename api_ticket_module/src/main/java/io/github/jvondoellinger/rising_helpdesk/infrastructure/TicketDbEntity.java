package io.github.jvondoellinger.rising_helpdesk.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter.InteractionHistoryConverter;
import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter.MentionsConverter;
import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter.QueueIdFieldConverter;
import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter.UserProfileIdFieldConverter;
import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.domain.Ticket;
import io.github.jvondoellinger.rising_helpdesk.domain.interaction.InteractionsHistory;
import io.github.jvondoellinger.rising_helpdesk.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
@FixAfter
public class TicketDbEntity {
	@Id
	private String number;

	private String title;

	@Column(name = "interactions")
	@Convert(converter = InteractionHistoryConverter.class)
	private InteractionsHistory history;

	private LocalDateTime deadline;

	@Column(name = "queue_id")
	@Convert(converter = QueueIdFieldConverter.class)
	private QueueId queueId;

	@Column

	@Convert(converter = MentionsConverter.class)
	private Mentions mentions;

	@CreationTimestamp
	private LocalDateTime openedOn;

	@Column(name = "opened_by_id")
	@Convert(converter = UserProfileIdFieldConverter.class)
	private UserProfileId openedBy;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime lastUpdatedOn;

	@Column(name = "last_updated_by_id")
	@Convert(converter = UserProfileIdFieldConverter.class)
	private UserProfileId lastUpdatedBy;

	@PersistenceCreator
	public TicketDbEntity(String number,
					  String title,
					  InteractionsHistory history,
					  LocalDateTime deadline,
					  QueueId queueId,
					  Mentions mentions,
					  LocalDateTime openedOn,
					  UserProfileId openedById,
					  LocalDateTime lastUpdatedOn,
					  UserProfileId lastUpdatedById) {
		this.number = number;
		this.title = title;
		this.history = history;
		this.deadline = deadline;
		this.queueId = queueId;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.openedBy = openedById;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedById;
	}

	public TicketDbEntity(Ticket ticket) {
/*		var mentions = (new ArrayList<>(ticket.mentions().readonlyList()))
			   .stream()
			   .map(UserProfileId::toString)
			   .toList();*/
		this.history = ticket.interactionHistory();

		this.queueId = ticket.queueId();
		this.number = ticket.number().toString();
		this.title = ticket.title();
		this.deadline = ticket.deadline();
		this.mentions = ticket.mentions();
		this.openedBy = ticket.openedBy();
		this.openedOn = ticket.openedOn();
		this.lastUpdatedBy = ticket.lastUpdatedBy() == null ? null : ticket.lastUpdatedBy();
		this.lastUpdatedOn = ticket.lastUpdatedOn();
	}

	protected TicketDbEntity() {}
}

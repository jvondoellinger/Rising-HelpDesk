package io.github.jvondoellinger.rising_helpdesk.domain;

import io.github.jvondoellinger.rising_helpdesk.domain.interaction.InteractionsHistory;
import io.github.jvondoellinger.rising_helpdesk.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public class Ticket {
	public Ticket(TicketNumber number,
			    String title,
			    InteractionsHistory history,
			    QueueId queue,
			    Mentions mentions,
			    LocalDateTime deadline,
			    UserProfileId openedBy,
			    LocalDateTime openedOn,
			    UserProfileId lastUpdatedBy,
			    LocalDateTime lastUpdatedOn) {
		this.number = number;
		this.title = title;
		this.history = history;
		this.queue = queue;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Ticket(String title,
			    InteractionsHistory history,
			    QueueId queue,
			    Mentions mentions,
			    UserProfileId openedBy,
			    LocalDateTime deadline) {
		this.number = TicketNumber.create();
		this.title = title;
		this.queue = queue;
		this.openedBy = openedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.history = history;
		this.openedOn = LocalDateTime.now();
		this.lastUpdatedOn = null;
		this.lastUpdatedBy = null;
	}

	private final TicketNumber number;
	private final String title;
	private final InteractionsHistory history;
	private final QueueId queue;
	private final LocalDateTime deadline;
	private final Mentions mentions;

	private final LocalDateTime openedOn;
	private final UserProfileId openedBy;
	private final LocalDateTime lastUpdatedOn;
	private final UserProfileId lastUpdatedBy;

	public TicketNumber number() {
		return number;
	}
	public String title() {
		return title;
	}
	public InteractionsHistory interactionHistory() {
		return history;
	}
	public QueueId queueId() {
		return queue;
	}
	public LocalDateTime deadline() {
		return deadline;
	}
	public Mentions mentions() {
		return mentions;
	}
	public LocalDateTime openedOn() {
		return openedOn;
	}
	public UserProfileId openedBy() {
		return openedBy;
	}
	public LocalDateTime lastUpdatedOn() {
		return lastUpdatedOn;
	}
	public UserProfileId lastUpdatedBy() {
		return lastUpdatedBy;
	}
}

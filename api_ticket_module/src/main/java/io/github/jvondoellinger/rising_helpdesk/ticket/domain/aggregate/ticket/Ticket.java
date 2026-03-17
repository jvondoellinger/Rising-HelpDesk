package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionsHistory;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ticket {
	public Ticket(UUID id,
				  TicketNumber number,
                  String title,
                  InteractionsHistory history,
                  UUID queueId,
                  List<Mention> mentions,
                  LocalDateTime deadline,
                  UUID openedBy,
                  LocalDateTime openedOn,
                  UUID lastUpdatedBy,
                  LocalDateTime lastUpdatedOn) {
        this.id = id;
        this.number = number;
		this.title = title;
		this.history = history;
		this.queueId = queueId;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Ticket(String title,
                  InteractionsHistory history,
                  UUID queueId,
                  UUID openedBy,
                  LocalDateTime deadline) {
        this.id = UUID.randomUUID();
        this.number = TicketNumber.create();
		this.title = title;
		this.queueId = queueId;
		this.openedBy = openedBy;
		this.deadline = deadline;
		this.mentions = new ArrayList<>();
		this.history = history;
		this.openedOn = LocalDateTime.now();
		this.lastUpdatedOn = null;
		this.lastUpdatedBy = null;
	}

	private final UUID id;
	private final TicketNumber number;
	private final String title;
	private final InteractionsHistory history;
	private final UUID queueId;
	private final LocalDateTime deadline;
	private final List<Mention> mentions;

	private final LocalDateTime openedOn;
	private final UUID openedBy;
	private final LocalDateTime lastUpdatedOn;
	private final UUID lastUpdatedBy;


	public void addMention(Mention mention) {
		mentions.add(mention);
	}

	// !Getters
	public UUID getId() {
		return id;
	}
	public TicketNumber getNumber() {
		return number;
	}
	public String getTitle() {
		return title;
	}
	public InteractionsHistory getHistory() {
		return history;
	}
	public UUID getQueueId() {
		return queueId;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public List<Mention> getMentions() {
		return List.copyOf(mentions); // Tornando imutavel pelo getter (eviantado gambiarra)
	}
	public LocalDateTime getOpenedOn() {
		return openedOn;
	}
	public UUID getOpenedBy() {
		return openedBy;
	}
	public LocalDateTime getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public UUID getLastUpdatedBy() {
		return lastUpdatedBy;
	}
}

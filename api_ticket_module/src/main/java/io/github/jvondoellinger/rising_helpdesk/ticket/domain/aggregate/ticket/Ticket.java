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
			    List<UUID> interactionIds,
			    UUID queueId,
			    List<UUID> mentions,
			    LocalDateTime deadline,
			    UUID openedBy,
			    LocalDateTime openedOn,
			    UUID lastUpdatedBy,
			    LocalDateTime lastUpdatedOn) {
		this.id = id;
		this.number = number;
		this.title = title;
		this.interactionIds = interactionIds;
		this.queueId = queueId;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Ticket(String title,
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
		this.interactionIds = new ArrayList<>();
		this.openedOn = LocalDateTime.now();
		this.lastUpdatedOn = null;
		this.lastUpdatedBy = null;
	}

	private final UUID id;
	private final TicketNumber number;
	private String title;
	private final List<UUID> interactionIds;
	private UUID queueId;
	private final LocalDateTime deadline;
	private final List<UUID> mentions;

	private final LocalDateTime openedOn;
	private final UUID openedBy;
	private LocalDateTime lastUpdatedOn;
	private UUID lastUpdatedBy;

	public void delegate(UUID queueId) {
		if (this.queueId.equals(queueId)) {
			throw new RuntimeException("The ticket is already in this queue.");
		}
		this.queueId = queueId;
	}
	public void addMention(UUID mentionId) {
		mentions.add(mentionId);
	}
	public void removeMention(UUID mentionId) {
		var optional = mentions
			   .stream()
			   .filter(x -> x.equals(mentionId))
			   .findAny();

		if (optional.isEmpty()) {
			return;
		}

		this.mentions.remove(optional.get());
	}
	public void updateTitle(UUID lastUpdatedBy, String title) {
		this.title = title;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedOn = LocalDateTime.now();
	}
	public void interact(UUID interactionId) {
		this.interactionIds.add(interactionId);
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
	public List<UUID> getInteractionIds() {
		return interactionIds;
	}
	public UUID getQueueId() {
		return queueId;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public List<UUID> getMentions() {
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

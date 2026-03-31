package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state.TicketState;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state.TicketStateFactory;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ticket {
	/**
		*@apiNote Ticket será preenchido conformes os valores forem adicionados no construtor
	 */
	public Ticket(UUID id,
			    TicketNumber number,
			    String title,
			    List<Interaction> interaction,
			    Queue queue,
			    List<Mention> mentions,
			    LocalDateTime deadline,
			    TicketState state,
			    UUID openedBy,
			    LocalDateTime openedOn,
			    UUID lastUpdatedBy,
			    LocalDateTime lastUpdatedOn) {
		this.id = id;
		this.number = number;
		this.title = title;
		this.interaction = interaction;
		this.queue = queue;
		this.state = state;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	/**
		*@apiNote Instancia um Ticket completamente novo
	 */
	public Ticket(String title,
			    Queue queue,
			    UUID openedBy,
			    LocalDateTime deadline) {
		this.id = UUID.randomUUID();
		this.number = TicketNumber.create();
		this.title = title;
		this.queue = queue;
		this.openedBy = openedBy;
		this.deadline = deadline;
		this.state = TicketStateFactory.from(TicketStatus.PENDING);
		this.mentions = new ArrayList<>();
		this.interaction = new ArrayList<>();
		this.openedOn = LocalDateTime.now();
		this.lastUpdatedOn = openedOn;
		this.lastUpdatedBy = openedBy;
	}

	// !!Properties
	private final UUID id;
	private final TicketNumber number;
	private String title;
	private final List<Interaction> interaction;
	private Queue queue;
	private final LocalDateTime deadline;
	private final List<Mention> mentions;
	private TicketState state;
	private final LocalDateTime openedOn;
	private final UUID openedBy;
	private LocalDateTime lastUpdatedOn;
	private UUID lastUpdatedBy;

	// !Functions
	public void delegate(Queue queue) {
		if (this.queue.equals(queue)) {
			throw new RuntimeException("The ticket is already in this queue.");
		}
		this.queue = queue;
	}
	public void addMention(Mention mentionId) {
		mentions.add(mentionId);
	}
	public void removeMention(Mention mentionId) {
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
	public void interact(Interaction interactionId) {
		this.interaction.add(interactionId);
	}
	public void updateState(TicketState state) {
		this.state = state;
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
	public List<Interaction> getInteraction() {
		return List.copyOf(interaction);
	}
	public Queue getQueue() {
		return queue;
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
	public TicketState getState() {
		return state;
	}
}

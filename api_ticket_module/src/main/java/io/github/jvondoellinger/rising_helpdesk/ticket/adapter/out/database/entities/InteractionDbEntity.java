package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities;

import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_interaction")
@Getter
@Setter
@FixAfter
public class InteractionDbEntity {
	@Id
	private UUID id;
	private String text;

	@Column
	private boolean visible;

	@Column(name = "interacted_by_id")
	private UUID interactedBy;

	@CreationTimestamp
	private LocalDateTime interactedOn;

	@ManyToOne(optional = false)
	private TicketDbEntity ticket;

	@PersistenceCreator
	public InteractionDbEntity(UUID id, String text, boolean visible, UUID interactedBy, LocalDateTime interactedOn, TicketDbEntity ticketDbEntity) {
		this.id = id;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
		this.ticket = ticketDbEntity;
	}

	public InteractionDbEntity() {}
}

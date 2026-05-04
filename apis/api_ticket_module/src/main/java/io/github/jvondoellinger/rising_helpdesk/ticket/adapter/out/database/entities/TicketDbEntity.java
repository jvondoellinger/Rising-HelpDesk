package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities;

import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
@FixAfter
public class TicketDbEntity {
	@Id
	private UUID id;

	@Column
	private String number;

	@Column
	private String title;

	@OneToMany(mappedBy = "ticket")
	private List<InteractionDbEntity> interactions;

	private LocalDateTime deadline;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private QueueDbEntity queue;

	// OneToMany para MençõesDbEntity e o hibernate gera um JOIN
	@OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
	private List<MentionDbEntity> mentions;

	@CreationTimestamp
	private LocalDateTime openedOn;

	@Column
	private String status;

	@Column(name = "opened_by")
	private UUID openedBy;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime lastUpdatedOn;

	@Column(name = "last_updated_by")
	private UUID lastUpdatedBy;

	@PersistenceCreator
	public TicketDbEntity(UUID id,
					  String number,
					  String title,
					  List<InteractionDbEntity> interactions,
					  LocalDateTime deadline,
					  QueueDbEntity queue,
					  List<MentionDbEntity> mentions,
					  LocalDateTime openedOn,
					  UUID openedById,
					  LocalDateTime lastUpdatedOn,
					  UUID lastUpdatedById) {
		this.number = number;
		this.title = title;
		this.interactions = interactions;
		this.deadline = deadline;
		this.queue = queue;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.openedBy = openedById;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedById;
	}

	public TicketDbEntity() {
	}

	public void addMentions(MentionDbEntity mentionDbEntity) {
		if (mentions == null)
			mentions = new ArrayList<>();

		mentions.add(mentionDbEntity);
	}

	public void addInteraction(InteractionDbEntity interactionDbEntity) {
		if (mentions == null)
			mentions = new ArrayList<>();

		this.interactions.add(interactionDbEntity);
	}

}

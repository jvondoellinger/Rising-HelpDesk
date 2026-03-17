package io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.converter.InteractionHistoryConverter;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.converter.UuidStringConverter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionsHistory;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
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

    private String number;

    private String title;

    @Column(name = "interactions")
    @Convert(converter = InteractionHistoryConverter.class)
    private InteractionsHistory history;

    private LocalDateTime deadline;

    @Column(name = "queue")
    private String queueId;

	// OneToMany para MençõesDbEntity e o hibernate gera um JOIN
	@OneToMany(mappedBy = "ticket")
	private List<MentionDbEntity> mentions;


    @CreationTimestamp
    private LocalDateTime openedOn;

    @Column(name = "opened_by")
    private String openedBy;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime lastUpdatedOn;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @PersistenceCreator
    public TicketDbEntity(UUID id,
						  String number,
                          String title,
                          InteractionsHistory history,
                          LocalDateTime deadline,
                          UUID queueId,
                          List<MentionDbEntity> mentions,
                          LocalDateTime openedOn,
                          UUID openedById,
                          LocalDateTime lastUpdatedOn,
                          UUID lastUpdatedById) {
        this.number = number;
        this.title = title;
        this.history = history;
        this.deadline = deadline;
        this.queueId = queueId.toString();
        this.mentions = mentions;
        this.openedOn = openedOn;
        this.openedBy = openedById.toString();
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedById.toString();
    }

    public TicketDbEntity() {
    }

	public void addMentions(MentionDbEntity mentionDbEntity) {
		if (mentions == null)
			mentions = new ArrayList<>();

		mentions.add(mentionDbEntity);
	}
}

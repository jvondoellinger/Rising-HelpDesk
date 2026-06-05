package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_mentions")
@Getter
@Setter
public class MentionDbEntity {
    @Id
    private UUID id;

    @Column
    private UUID mentionedByUserId;

    @ManyToOne
    @JoinColumn(name = "ticket")
    private TicketDbEntity ticket;

    @CreationTimestamp
    private LocalDateTime mentionedAt;

    public MentionDbEntity() {
    }

    @PersistenceCreator
    public MentionDbEntity(UUID id, UUID mentionedByUserId, UUID userProfileId, LocalDateTime mentionedAt, TicketDbEntity ticket) {
        this.id = id;
        this.mentionedByUserId = mentionedByUserId;
        this.ticket = ticket;
        this.mentionedAt = mentionedAt;
    }
}

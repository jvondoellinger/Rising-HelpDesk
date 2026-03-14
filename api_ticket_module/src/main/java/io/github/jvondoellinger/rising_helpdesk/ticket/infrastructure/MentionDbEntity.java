package io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.mention.Mentions;
import jakarta.annotation.PostConstruct;
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
@FixAfter
public class MentionDbEntity {
    @Id
    private UUID uuid;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private TicketDbEntity ticket;
    private UserProfileId userProfileId;

    @CreationTimestamp
    private LocalDateTime mentionedAt;

    public MentionDbEntity(Mentions mentions) {

    }

    @PersistenceCreator
    public MentionDbEntity(UUID uuid, TicketDbEntity ticket, UserProfileId userProfileId, LocalDateTime mentionedAt) {
        this.uuid = uuid;
        this.ticket = ticket;
        this.userProfileId = userProfileId;
        this.mentionedAt = mentionedAt;
    }
}

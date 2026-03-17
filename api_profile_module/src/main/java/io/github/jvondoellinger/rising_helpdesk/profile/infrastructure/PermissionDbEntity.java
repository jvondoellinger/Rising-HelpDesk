package io.github.jvondoellinger.rising_helpdesk.profile.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_permissions")
@Getter
@Setter
public class PermissionDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String permission;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @PersistenceCreator
    public PermissionDbEntity(UUID id, String permission, LocalDateTime createdAt) {
        this.id = id;
        this.permission = permission;
        this.createdAt = createdAt;
    }

    public PermissionDbEntity() {
    }
}

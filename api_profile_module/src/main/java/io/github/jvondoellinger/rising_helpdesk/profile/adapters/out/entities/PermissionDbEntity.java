package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities;

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
    private String code;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @PersistenceCreator
    public PermissionDbEntity(UUID id, String code, LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.createdAt = createdAt;
    }

    public PermissionDbEntity() {
    }
}

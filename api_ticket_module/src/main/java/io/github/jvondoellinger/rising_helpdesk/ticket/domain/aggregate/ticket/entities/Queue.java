package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Queue {
    private final UUID id;
    private final String area;
    private final String subarea;
    private final LocalDateTime createdAt;
    private final UUID createdBy;
    private final LocalDateTime updatedAt;
    private final UUID lastUpdatedBy;

    public Queue(UUID id,
                 String area,
                 String subarea,
                 UUID createdBy,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 UUID lastUpdatedBy) {
        this.id = id;
        this.area = area;
        this.subarea = subarea;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Queue(String area, String subarea, UUID createdBy) {
        this.id = UUID.randomUUID();
        this.area = area;
        this.subarea = subarea;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.lastUpdatedBy = null;
    }

    public UUID getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getSubarea() {
        return subarea;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}

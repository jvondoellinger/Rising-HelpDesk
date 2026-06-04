package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities;

import java.time.LocalDateTime;
import java.util.Objects;
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
        this.updatedAt = LocalDateTime.now();
        this.lastUpdatedBy = createdBy;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Queue queue = (Queue) o;
        return Objects.equals(id, queue.id) &&
                Objects.equals(area, queue.area) &&
                Objects.equals(subarea, queue.subarea) &&
                Objects.equals(createdAt, queue.createdAt) &&
                Objects.equals(createdBy, queue.createdBy) &&
                Objects.equals(updatedAt, queue.updatedAt) &&
                Objects.equals(lastUpdatedBy, queue.lastUpdatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, subarea, createdAt, createdBy, updatedAt, lastUpdatedBy);
    }
}

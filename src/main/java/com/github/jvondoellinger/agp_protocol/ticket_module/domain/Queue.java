package com.github.jvondoellinger.agp_protocol.ticket_module.domain;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;

import java.time.LocalDateTime;

public class Queue {
	private final QueueId domainId;
	private final String area;
	private final String subarea;
	private final LocalDateTime createdAt;
	private final UserProfileId createdBy;
	private final LocalDateTime updatedAt;
	private final UserProfileId lastUpdatedBy;

	public Queue(QueueId id,
			   String area,
			   String subarea,
			   UserProfileId createdBy,
			   LocalDateTime createdAt,
			   LocalDateTime updatedAt,
			   UserProfileId lastUpdatedBy) {
		this.domainId = id;
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Queue(String area, String subarea, UserProfileId createdBy) {
		this.domainId = new QueueId();
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
		this.lastUpdatedBy = null;
	}

	public QueueId getDomainId() {
		return domainId;
	}
	public String getArea() {
		return area;
	}
	public String getSubarea() {
		return subarea;
	}
	public UserProfileId getCreatedBy() {
		return createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public UserProfileId getLastUpdatedBy() {
		return lastUpdatedBy;
	}
}

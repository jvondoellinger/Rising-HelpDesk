package com.github.jvondoellinger.agp_protocol.ticket_module.domain;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;

import java.time.LocalDateTime;

public class Queue {
	private final DomainId domainId;
	private final String area;
	private final String subarea;
	private final LocalDateTime createdAt;
	private final UserProfileId createdBy;
	private final LocalDateTime updatedAt;
	private final UserProfileId lastUpdatedBy;

	public Queue(DomainId domainId,
			   String area,
			   String subarea,
			   UserProfileId createdBy,
			   LocalDateTime createdAt,
			   LocalDateTime updatedAt,
			   UserProfileId lastUpdatedBy) {
		this.domainId = domainId;
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Queue(String area, String subarea, UserProfileId createdBy) {
		this.domainId = DomainId.create();
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
		this.lastUpdatedBy = null;
	}

	public DomainId getDomainId() {
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

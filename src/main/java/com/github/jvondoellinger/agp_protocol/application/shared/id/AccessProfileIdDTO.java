package com.github.jvondoellinger.agp_protocol.application.shared.id;

public record AccessProfileIdDTO(String accessProfileId) implements DomainIdDTO {
	@Override
	public String id() {
		return accessProfileId;
	}
}

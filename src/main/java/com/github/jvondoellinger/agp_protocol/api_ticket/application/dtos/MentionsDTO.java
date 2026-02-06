package com.github.jvondoellinger.agp_protocol.api_ticket.application.dtos;

import java.util.List;

public record MentionsDTO(List<String> userIds) {
	public MentionsDTO {
		userIds = List.copyOf(userIds);
	}
}

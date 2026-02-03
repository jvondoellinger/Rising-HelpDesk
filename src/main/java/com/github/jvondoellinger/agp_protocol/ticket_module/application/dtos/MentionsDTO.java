package com.github.jvondoellinger.agp_protocol.ticket_module.application.dtos;

import java.util.List;

public record MentionsDTO(List<String> userIds) {
	public MentionsDTO {
		userIds = List.copyOf(userIds);
	}
}

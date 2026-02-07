package io.github.jvondoellinger.rising_helpdesk.application.dtos;

import java.util.List;

public record MentionsDTO(List<String> userIds) {
	public MentionsDTO {
		userIds = List.copyOf(userIds);
	}
}

package io.github.jvondoellinger.rising_helpdesk.ticket.repository.integration.builders;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;

import java.util.Random;
import java.util.UUID;

public class CreateQueueRequestTestBuilder {
	private UUID createdBy = UUID.fromString("00000000-0000-0000-0000-000000000000");
	private String area = "TEST_AREA (%s)".formatted(Math.random());
	private String subarea = "TEST_SUBAREA (%s)".formatted(Math.random());

	public CreateQueueRequest build() {
		return new CreateQueueRequest(area, subarea,createdBy );
	}
}

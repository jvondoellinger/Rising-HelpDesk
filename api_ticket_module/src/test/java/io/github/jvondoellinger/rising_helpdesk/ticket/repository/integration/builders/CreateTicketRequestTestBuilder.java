package io.github.jvondoellinger.rising_helpdesk.ticket.repository.integration.builders;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.CreateTicketRequest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateTicketRequestTestBuilder {
	private String title = "TEST_TILE";
	private UUID queueId = null;
	private LocalDateTime deadline = LocalDateTime.now();

	public CreateTicketRequestTestBuilder withQueueId(UUID queueId) {
		this.queueId = queueId;
		return this;
	}

	public CreateTicketRequest build() {
		return new CreateTicketRequest(
			   title,
			   queueId,
			   deadline
		);
	}
}

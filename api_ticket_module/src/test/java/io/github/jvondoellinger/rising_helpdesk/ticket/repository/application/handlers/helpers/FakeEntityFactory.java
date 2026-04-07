package io.github.jvondoellinger.rising_helpdesk.ticket.repository.application.handlers.helpers;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;

import java.time.LocalDateTime;
import java.util.UUID;

public class FakeEntityFactory {
	public static String fake_title = "TEST_TICKET_TITLE";
	public static Ticket ticket(Queue queue) {
		return new Ticket(fake_title,
			   queue,
			   UUID.randomUUID(),
			   LocalDateTime.now().plusDays(5));
	}

	public static String fake_area = "TEST_AREA";
	public static String fake_subarea = "TEST_SUBAREA";
	public static Queue queue() {
		return new Queue(fake_area, fake_subarea, UUID.randomUUID());
	}

	public static String fake_text = "TEST_INTERACTION";
	public static Interaction interaction(Ticket tk) {
		return new Interaction(fake_text, UUID.randomUUID(), tk.getId());
	}
}

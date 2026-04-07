package io.github.jvondoellinger.rising_helpdesk.ticket.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddInteractionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.AddInteractionService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddInteractionCommandHandlerTest implements UnitTest {
	@Mock
	private InteractionRepository repository;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private CurrentUserService currentUserService;
	@InjectMocks
	private AddInteractionService service;

	private Ticket tk;
	private AddInteractionCommand command;

	@BeforeEach
	void setup() {
		var queue = FakeEntityFactory.queue();
		var tk = FakeEntityFactory.ticket(queue);
		this.tk = tk;

		command = new AddInteractionCommand(
			   FakeEntityFactory.fake_text,
			   tk.getId()
		);
	}

	@Test
	void shouldAddInteractionSuccessfully() {
		when(ticketRepository.findById(command.ticketId())).thenReturn(Optional.of(tk));
		when(currentUserService.getUserId())
	}
}

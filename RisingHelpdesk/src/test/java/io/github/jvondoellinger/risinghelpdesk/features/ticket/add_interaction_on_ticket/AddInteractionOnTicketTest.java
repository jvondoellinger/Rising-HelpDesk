package io.github.jvondoellinger.risinghelpdesk.features.ticket.add_interaction_on_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_on_ticket.AddInteractionOnTicket;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_on_ticket.AddInteractionOnTicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddInteractionOnTicketTest {
	@Mock
	private InteractionRepository repository;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private AuthenticatedUser authenticatedUser;
	@InjectMocks
	private AddInteractionOnTicketService service;

	private UUID ticketId;
	private AddInteractionOnTicket cmd;

	@BeforeEach
	void setup() {
		ticketId = UUID.randomUUID();
		cmd = new AddInteractionOnTicket("TEST", ticketId);
	}

	@Test
	void deveRetornarErroPorNãoExistirUsuario() {
		when(ticketRepository.existsById(ticketId)).thenReturn(false);

		var result = service.handle(cmd);

		assertTrue(result.hasErrors());

		verify(ticketRepository).existsById(ticketId);
		verifyNoInteractions(repository);
	}
}

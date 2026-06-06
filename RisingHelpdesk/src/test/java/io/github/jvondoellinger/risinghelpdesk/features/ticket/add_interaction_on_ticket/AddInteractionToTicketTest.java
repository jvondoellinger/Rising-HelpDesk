package io.github.jvondoellinger.risinghelpdesk.features.ticket.add_interaction_on_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_to_ticket.AddInteractionToTicket;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_to_ticket.AddInteractionToTicketService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddInteractionToTicketTest {
	@Mock
	private InteractionRepository interactionRepository;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private AuthenticatedUser authenticatedUser;
	@InjectMocks
	private AddInteractionToTicketService service;

	private UUID ticketId;
	private AddInteractionToTicket cmd;

	@BeforeEach
	void setup() {
		ticketId = UUID.randomUUID();
		cmd = new AddInteractionToTicket("TEST", ticketId);
	}

	@Test
	@DisplayName("Deve retornar erro por nãe existir um ticket para associar a interação")
	void deveRetornarErroPorNãoExistirTicket() {
		when(ticketRepository.existsById(ticketId)).thenReturn(false);

		var result = service.handle(cmd);

		System.out.println(result.get().getValue2());

		assertTrue(result.hasErrors());

		verify(ticketRepository).existsById(ticketId);
		verifyNoInteractions(interactionRepository);
	}

	@Test
	@DisplayName("Deve retornar sucesso por existir ticket.")
	void deveRetornarSucessoPorExistirTicket() {
		when(ticketRepository.existsById(ticketId)).thenReturn(true);

		var result = service.handle(cmd);

		assertFalse(result.hasErrors());

		verify(ticketRepository, times(1)).existsById(ticketId);
		verifyNoMoreInteractions(ticketRepository);

		verify(interactionRepository, times(1)).save(any());
		verifyNoMoreInteractions(interactionRepository);
	}
}

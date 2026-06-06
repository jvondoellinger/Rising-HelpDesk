package io.github.jvondoellinger.risinghelpdesk.features.ticket.add_ticket_mention;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.MentionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Mention;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_ticket_mention.AddTicketMention;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_ticket_mention.AddTicketMentionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddTicketMentionTest {
	@Mock
	TicketRepository ticketRepository;
	@Mock
	MentionRepository mentionRepository;
	@Mock
	AuthenticatedUser authenticatedUser;
	@InjectMocks
	AddTicketMentionService service;

	UUID ticketId;
	UUID userId;
	AddTicketMention cmd;
	Mention mention;
	@BeforeEach
	void setup() {
		ticketId = UUID.randomUUID();
		userId = UUID.randomUUID();
		cmd = new AddTicketMention(userId, ticketId);
		mention = new Mention(userId, userId, ticketId);
	}

	@Test
	@DisplayName("Deve retornar erro por nãe existir um ticket para associar a interação")
	void deveRetornarErroPorNãoExistirTicket() {
		when(ticketRepository.existsById(ticketId)).thenReturn(false);

		var result = service.handle(cmd);

		System.out.println(result.get().getValue2());

		assertTrue(result.hasErrors());

		verify(ticketRepository).existsById(ticketId);
		verifyNoMoreInteractions(ticketRepository);
	}

	@Test
	@DisplayName("Deve retornar sucesso por existir ticket.")
	void deveRetornarSucessoPorExistirTicket() {
		when(ticketRepository.existsById(ticketId)).thenReturn(true);

		var result = service.handle(cmd);

		assertFalse(result.hasErrors());

		verify(ticketRepository, times(1)).existsById(ticketId);
		verifyNoMoreInteractions(ticketRepository);

		verify(mentionRepository, times(1)).save(any());
		verifyNoMoreInteractions(mentionRepository);
	}
}

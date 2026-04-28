package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.CloseTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.CloseTicketCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CloseTicketCommandServiceTest implements UnitTest {

	@Mock
	private TicketRepository repository;

	@InjectMocks
	private CloseTicketCommandService service;

	@Captor
	private ArgumentCaptor<Ticket> ticketCaptor;

	private CloseTicketCommand command;
	private Ticket ticket;

	@BeforeEach
	void setUp() {
		var queue = FakeEntityFactory.queue();
		ticket = FakeEntityFactory.ticket(queue);
		command = new CloseTicketCommand(ticket.getId());
	}

	@Test
	@DisplayName("Deve fechar ticket quando existir")
	void shouldCloseTicketWhenFound() {
		when(repository.findById(command.ticketId())).thenReturn(Optional.of(ticket));

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(ticketCaptor.capture());
		assertThat(ticketCaptor.getValue().getState().getStatus()).isEqualTo(TicketStatus.CLOSED);
	}

	@Test
	@DisplayName("Deve retornar erro quando o ticket não existir")
	void shouldFailWhenTicketNotFound() {
		when(repository.findById(command.ticketId())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(Ticket.class));
	}
}

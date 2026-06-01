package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.DelegateTicketCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DelegateTicketCommandHandlerTest implements UnitTest {

	@Mock
	private TicketRepository repository;
	@Mock
	private QueueRepository queueRepository;

	@InjectMocks
	private DelegateTicketCommandService service;

	@Captor
	private ArgumentCaptor<Ticket> ticketCaptor;

	private Ticket ticket;
	private Queue sourceQueue;
	private Queue targetQueue;
	private DelegateTicketCommand command;

	@BeforeEach
	void setup() {
		sourceQueue = FakeEntityFactory.queue();
		targetQueue = new Queue("TEST_OTHER_AREA", "TEST_OTHER_SUBAREA", UUID.randomUUID());
		ticket = FakeEntityFactory.ticket(sourceQueue);
		command = new DelegateTicketCommand(ticket.getId(), targetQueue.getId());
	}

	@Test
	@DisplayName("Deve delegar o ticket para outra fila quando fila e ticket existirem")
	void shouldDelegateTicketWhenQueueAndTicketExist() {
		when(queueRepository.findById(command.queueId())).thenReturn(Optional.of(targetQueue));
		when(repository.findById(command.ticketId())).thenReturn(Optional.of(ticket));

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(ticketCaptor.capture());

		var saved = ticketCaptor.getValue();
		assertThat(saved.getQueue().getId()).isEqualTo(targetQueue.getId());
	}

	@Test
	@DisplayName("Deve retornar erro quando a fila de destino não for encontrada")
	void shouldReturnErrorWhenTargetQueueNotFound() {
		when(queueRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(Ticket.class));
	}

	@Test
	@DisplayName("Deve retornar erro quando o ticket não for encontrado")
	void shouldReturnErrorWhenTicketNotFound() {
		when(queueRepository.findById(command.queueId())).thenReturn(Optional.of(targetQueue));
		when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(Ticket.class));
	}
}

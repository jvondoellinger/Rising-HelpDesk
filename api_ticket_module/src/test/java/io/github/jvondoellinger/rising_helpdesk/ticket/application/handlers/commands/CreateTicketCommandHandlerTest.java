package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.CreateTicketCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTicketCommandHandlerTest implements UnitTest {
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private QueueRepository queueRepository;
	@Mock
	private CurrentUserService currentUserService;
	@InjectMocks
	private CreateTicketCommandService service;
	@Captor
	private ArgumentCaptor<Ticket> ticketCaptor;

	private Queue queue;
	private CreateTicketCommand command;

	@BeforeEach
	void setup() {
		queue = FakeEntityFactory.queue();
		command = new CreateTicketCommand(
			   "TEST_TICKET_TITLE",
			   queue.getId(),
			   LocalDateTime.now().plusDays(5)
		);
	}

	@Test
	@DisplayName("Deve criar um ticket com sucesso quando existir a fila!")
	void shouldCreateTicketSuccessfully() {
		when(queueRepository.findById(queue.getId())).thenReturn(Optional.of(queue));
		when(currentUserService.getUserId()).thenReturn(UUID.randomUUID());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(ticketRepository).save(ticketCaptor.capture());

		var tk = ticketCaptor.getValue();

		assertThat(tk.getTitle()).isEqualTo(command.title());
		assertThat(tk.getQueue().getId()).isEqualTo(command.queueId());
		assertThat(tk.getDeadline()).isEqualTo(command.deadline());
	}

	@Test
	@DisplayName("Deve retornar erro quando queue não é encontrada")
	void shouldReturnErrorWhenQueueNotFound() {
		when(queueRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).isNotNull();
		assertThat(result.getError()).containsIgnoringCase("queue");

		verify(ticketRepository, never()).save(any(Ticket.class));
	}
}

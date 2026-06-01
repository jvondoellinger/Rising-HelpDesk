package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.RemoveQueueService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class RemoveQueueServiceTest implements UnitTest {

	@Mock
	private QueueRepository repository;

	@InjectMocks
	private RemoveQueueService service;

	private RemoveQueueCommand command;
	private Queue queue;

	@BeforeEach
	void setUp() {
		queue = FakeEntityFactory.queue();
		command = new RemoveQueueCommand(queue.getId());
	}

	@Test
	@DisplayName("Deve remover fila quando existir")
	void shouldRemoveQueueWhenFound() {
		when(repository.findById(command.queueId())).thenReturn(Optional.of(queue));

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).delete(queue);
	}

	@Test
	@DisplayName("Deve retornar erro quando a fila não existir")
	void shouldFailWhenQueueNotFound() {
		when(repository.findById(command.queueId())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).delete(any(Queue.class));
	}
}

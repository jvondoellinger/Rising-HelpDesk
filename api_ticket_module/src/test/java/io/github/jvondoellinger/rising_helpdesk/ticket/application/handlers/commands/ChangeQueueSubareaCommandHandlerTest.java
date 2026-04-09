package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.ChangeQueueSubareaCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
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
public class ChangeQueueSubareaCommandHandlerTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private CurrentUserService currentUserService;

	@InjectMocks
	private ChangeQueueSubareaCommandService service;

	@Captor
	private ArgumentCaptor<Queue> queueCaptor;

	private Queue queue;
	private ChangeQueueSubareaCommand command;
	private UUID editorId;

	@BeforeEach
	void setup() {
		queue = FakeEntityFactory.queue();
		command = new ChangeQueueSubareaCommand(queue.getId(), "TEST_SUBAREA_UPDATED");
		editorId = UUID.randomUUID();
	}

	@Test
	@DisplayName("Deve alterar a subárea quando a fila existir e o valor não coincidir com a área atual")
	void shouldUpdateSubareaWhenQueueExistsAndValueIsAllowed() {
		when(repository.findById(command.id())).thenReturn(Optional.of(queue));
		when(currentUserService.getUserId()).thenReturn(editorId);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(queueCaptor.capture());

		var saved = queueCaptor.getValue();
		assertThat(saved.getId()).isEqualTo(queue.getId());
		assertThat(saved.getSubarea()).isEqualTo(command.subarea());
		assertThat(saved.getArea()).isEqualTo(queue.getArea());
		assertThat(saved.getLastUpdatedBy()).isEqualTo(editorId);
	}

	@Test
	@DisplayName("Deve retornar erro quando a fila não for encontrada")
	void shouldReturnErrorWhenQueueNotFound() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(Queue.class));
	}
}

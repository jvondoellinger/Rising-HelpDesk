package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.CreateQueueCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateQueueCommandHandlerTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private QueueMapper mapper;
	@Mock
	private CurrentUserService currentUserService;

	@InjectMocks
	private CreateQueueCommandService service;

	@Captor
	private ArgumentCaptor<Queue> queueCaptor;

	private CreateQueueCommand command;
	private UUID authorId;

	@BeforeEach
	void setup() {
		command = new CreateQueueCommand(
			   "TEST_AREA",
			   "TEST_SUBAREA"
		);
		authorId = UUID.randomUUID();
	}

	@Test
	@DisplayName("Deve criar fila com sucesso quando a área ainda não existe")
	void shouldCreateQueueWhenAreaIsAvailable() {
		when(repository.existsByArea(command.area())).thenReturn(false);
		when(currentUserService.getUserId()).thenReturn(authorId);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(queueCaptor.capture());

		var saved = queueCaptor.getValue();
		assertThat(saved.getArea()).isEqualTo(command.area());
		assertThat(saved.getSubarea()).isEqualTo(command.subarea());
		assertThat(saved.getCreatedBy()).isEqualTo(authorId);
	}

	@Test
	@DisplayName("Deve retornar erro quando a área já existe")
	void shouldReturnErrorWhenAreaAlreadyExists() {
		when(repository.existsByArea(anyString())).thenReturn(true);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(Queue.class));
	}
}

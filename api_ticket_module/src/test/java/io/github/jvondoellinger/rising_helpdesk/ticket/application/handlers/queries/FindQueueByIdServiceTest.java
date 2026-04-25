package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindQueueByIdService;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindQueueByIdServiceTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private QueueMapper mapper;

	@InjectMocks
	private FindQueueByIdService service;

	private FindQueueByIdQuery query;
	private Queue queue;
	private QueueDetails details;

	@BeforeEach
	void setUp() {
		queue = FakeEntityFactory.queue();
		query = new FindQueueByIdQuery(queue.getId());
		details = new QueueDetails(
			   queue.getId(),
			   queue.getArea(),
			   queue.getSubarea(),
			   queue.getCreatedAt(),
			   queue.getCreatedBy(),
			   queue.getUpdatedAt(),
			   queue.getLastUpdatedBy()
		);
	}

	@Test
	@DisplayName("Deve retornar fila quando o id existir")
	void shouldReturnQueueWhenFound() {
		when(repository.findById(query.id())).thenReturn(Optional.of(queue));
		when(mapper.details(queue)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().id()).isEqualTo(query.id());
	}

	@Test
	@DisplayName("Deve retornar erro quando a fila não existir")
	void shouldFailWhenNotFound() {
		when(repository.findById(query.id())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError().description()).containsIgnoringCase("queue");
	}
}

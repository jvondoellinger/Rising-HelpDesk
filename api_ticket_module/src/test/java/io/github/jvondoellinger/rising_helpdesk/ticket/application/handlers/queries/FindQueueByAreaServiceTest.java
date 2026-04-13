package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAreaQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindQueueByAreaService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindQueueByAreaServiceTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private QueueMapper mapper;

	@InjectMocks
	private FindQueueByAreaService service;

	private FindQueueByAreaQuery query;
	private Queue queue;
	private QueueDetails details;

	@BeforeEach
	void setUp() {
		queue = FakeEntityFactory.queue();
		query = new FindQueueByAreaQuery(queue.getArea());
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
	@DisplayName("Deve retornar fila quando existir para a área informada")
	void shouldReturnQueueWhenFound() {
		when(repository.findBySubarea(query.area())).thenReturn(Optional.of(queue));
		when(mapper.details(queue)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().id()).isEqualTo(queue.getId());
	}

	@Test
	@DisplayName("Deve retornar erro quando não existir fila para a área")
	void shouldFailWhenNotFound() {
		when(repository.findBySubarea(query.area())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("queue");
	}
}

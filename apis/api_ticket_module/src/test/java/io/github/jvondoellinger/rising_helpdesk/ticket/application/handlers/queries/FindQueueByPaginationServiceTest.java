package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindQueueByPaginationService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindQueueByPaginationServiceTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private QueueMapper mapper;

	@InjectMocks
	private FindQueueByPaginationService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindQueueByPaginationQuery query;
	private Pagination<Queue> domainPage;
	private Queue queue;

	@BeforeEach
	void setUp() {
		queue = FakeEntityFactory.queue();
		query = new FindQueueByPaginationQuery(1, 12);
		domainPage = Pagination.of(List.of(queue), 1, 1);
	}

	@Test
	@DisplayName("Deve retornar filas paginadas e repassar page e size ao repositório")
	void shouldReturnPaginatedQueues() {
		when(repository.findByPagination(any(PaginationFilter.class))).thenReturn(domainPage);
		when(mapper.details(queue)).thenReturn(
			   new io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails(
					 queue.getId(),
					 queue.getArea(),
					 queue.getSubarea(),
					 queue.getCreatedAt(),
					 queue.getCreatedBy(),
					 queue.getUpdatedAt(),
					 queue.getLastUpdatedBy()
			   )
		);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().items()).hasSize(1);
		verify(repository).findByPagination(filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

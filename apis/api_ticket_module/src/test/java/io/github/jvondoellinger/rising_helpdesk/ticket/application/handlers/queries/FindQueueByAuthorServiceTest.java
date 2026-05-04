package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindQueueByAuthorService;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindQueueByAuthorServiceTest implements UnitTest {

	@Mock
	private QueueRepository repository;
	@Mock
	private QueueMapper mapper;

	@InjectMocks
	private FindQueueByAuthorService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindQueueByAuthorQuery query;
	private UUID authorId;

	@BeforeEach
	void setUp() {
		authorId = UUID.randomUUID();
		query = new FindQueueByAuthorQuery(authorId, 0, 10);
	}

	@Test
	@DisplayName("Deve retornar erro quando não houver filas do autor")
	void shouldFailWhenEmpty() {
		when(repository.findByAuthor(eq(authorId), any(PaginationFilter.class))).thenReturn(Pagination.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError().description()).containsIgnoringCase("queue");
	}

	@Test
	@DisplayName("Deve retornar filas paginadas quando o autor possuir registros")
	void shouldReturnQueuesWhenFound() {
		var queue = FakeEntityFactory.queue();
		var page = Pagination.of(List.of(queue), 0, 1);
		when(repository.findByAuthor(eq(authorId), any(PaginationFilter.class))).thenReturn(page);
		when(mapper.detailsPagination(page)).thenReturn(Pagination.of(List.of(), query.page(), page.totalPages()));

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).findByAuthor(eq(authorId), filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

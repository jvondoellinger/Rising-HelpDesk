package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindTicketByPaginationService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindTicketByPaginationServiceTest implements UnitTest {

	@Mock
	private TicketRepository repository;
	@Mock
	private TicketMapper mapper;

	@InjectMocks
	private FindTicketByPaginationService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindTicketByPaginationQuery query;
	private Pagination<Ticket> domainPage;
	private Ticket ticket;

	@BeforeEach
	void setUp() {
		var queue = FakeEntityFactory.queue();
		ticket = FakeEntityFactory.ticket(queue);
		query = new FindTicketByPaginationQuery(1, 5);
		domainPage = Pagination.of(List.of(ticket), 1, 1);
	}

	@Test
	@DisplayName("Deve retornar tickets paginados repassando page e size ao repositório")
	void shouldReturnPaginatedTickets() {
		when(repository.findByPagination(any(PaginationFilter.class))).thenReturn(domainPage);
		when(mapper.detailsPagination(domainPage)).thenReturn(Pagination.of(List.of(), query.page(), domainPage.totalPages()));

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).findByPagination(filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketsByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindTicketByAuthorService;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindTicketByAuthorServiceTest implements UnitTest {

	@Mock
	private TicketRepository repository;
	@Mock
	private TicketMapper mapper;

	@InjectMocks
	private FindTicketByAuthorService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindTicketsByAuthorQuery query;
	private UUID authorId;
	private Ticket ticket;

	@BeforeEach
	void setUp() {
		authorId = UUID.randomUUID();
		query = new FindTicketsByAuthorQuery(authorId, 2, 8);
		var queue = FakeEntityFactory.queue();
		ticket = FakeEntityFactory.ticket(queue);
	}

	@Test
	@DisplayName("Deve retornar tickets do autor mapeados em paginação")
	void shouldReturnTicketsByAuthor() {
		var page = Pagination.of(List.of(ticket), 2, 1);
		var q = ticket.getQueue();
		var queueDetails = new QueueDetails(
			   q.getId(),
			   q.getArea(),
			   q.getSubarea(),
			   q.getCreatedAt(),
			   q.getCreatedBy(),
			   q.getUpdatedAt(),
			   q.getLastUpdatedBy()
		);
		var mapped = new TicketDetails(
			   ticket.getId(),
			   ticket.getNumber(),
			   ticket.getTitle(),
			   queueDetails,
			   ticket.getMentions(),
			   ticket.getDeadline(),
			   ticket.getOpenedBy(),
			   ticket.getOpenedOn(),
			   ticket.getLastUpdatedBy(),
			   ticket.getLastUpdatedOn()
		);
		when(repository.findByAuthorId(eq(authorId), any(PaginationFilter.class))).thenReturn(page);
		when(mapper.details(ticket)).thenReturn(mapped);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().items()).hasSize(1);
		verify(repository).findByAuthorId(eq(authorId), filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

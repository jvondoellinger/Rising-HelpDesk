package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByNumberQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindTicketByNumberQueryHandlerImpl;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
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
class FindTicketByNumberQueryHandlerImplTest implements UnitTest {

	@Mock
	private TicketRepository repository;
	@Mock
	private TicketMapper mapper;

	@InjectMocks
	private FindTicketByNumberQueryHandlerImpl service;

	private FindTicketByNumberQuery queryWithNumber;
	private Ticket ticket;

	@BeforeEach
	void setUp() {
		var queue = FakeEntityFactory.queue();
		ticket = FakeEntityFactory.ticket(queue);
		queryWithNumber = new FindTicketByNumberQuery(ticket.getNumber());
	}

	@Test
	@DisplayName("Deve retornar erro quando o número for nulo")
	void shouldFailWhenNumberBlank() {
		var result = service.handle(new FindTicketByNumberQuery(null));

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("Protocol");
	}

	@Test
	@DisplayName("Deve retornar erro quando não existir ticket com o número")
	void shouldFailWhenNotFound() {
		when(repository.findByNumber(queryWithNumber.number())).thenReturn(Optional.empty());

		var result = service.handle(queryWithNumber);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("ticket");
	}

	@Test
	@DisplayName("Deve retornar detalhes quando existir ticket com o número")
	void shouldReturnDetailsWhenFound() {
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
		var details = new TicketDetails(
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
		when(repository.findByNumber(queryWithNumber.number())).thenReturn(Optional.of(ticket));
		when(mapper.details(ticket)).thenReturn(details);

		var result = service.handle(queryWithNumber);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().title()).isEqualTo(ticket.getTitle());
	}
}

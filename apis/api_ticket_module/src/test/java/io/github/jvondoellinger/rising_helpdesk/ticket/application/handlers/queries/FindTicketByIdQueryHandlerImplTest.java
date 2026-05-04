package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query.FindTicketByIdQueryHandlerImpl;
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
class FindTicketByIdQueryHandlerImplTest implements UnitTest {

	@Mock
	private TicketRepository repository;
	@Mock
	private TicketMapper mapper;

	@InjectMocks
	private FindTicketByIdQueryHandlerImpl service;

	private FindTicketByIdQuery queryWithId;
	private Ticket ticket;

	@BeforeEach
	void setUp() {
		var queue = FakeEntityFactory.queue();
		ticket = FakeEntityFactory.ticket(queue);
		queryWithId = new FindTicketByIdQuery(ticket.getId());
	}

	@Test
	@DisplayName("Deve retornar erro quando o id for nulo")
	void shouldFailWhenIdBlank() {
		var result = service.handle(new FindTicketByIdQuery(null));

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError().description()).containsIgnoringCase("ID");
	}

	@Test
	@DisplayName("Deve retornar sucesso sem valor quando o ticket não existir")
	void shouldReturnEmptySuccessWhenNotFound() {
		when(repository.findById(queryWithId.id())).thenReturn(Optional.empty());

		var result = service.handle(queryWithId);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue()).isNull();
	}

	@Test
	@DisplayName("Deve retornar detalhes quando o ticket existir")
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
		when(repository.findById(queryWithId.id())).thenReturn(Optional.of(ticket));
		when(mapper.details(ticket)).thenReturn(details);

		var result = service.handle(queryWithId);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().id()).isEqualTo(ticket.getId());
	}
}

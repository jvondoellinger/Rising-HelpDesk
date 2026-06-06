package io.github.jvondoellinger.risinghelpdesk.features.ticket.change_ticket_queue;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.state.TicketStateFactory;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status.TicketStatus;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue.ChangeTicketQueue;
import io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue.ChangeTicketQueueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChangeTicketQueueTest {
	@Mock
	TicketRepository ticketRepository;
	@Mock
	QueueRepository queueRepository;

	@InjectMocks
	ChangeTicketQueueService service;

	UUID queueId;
	UUID ticketId;
	ChangeTicketQueue cmd;
	UUID newQueueId;

	Ticket ticket;
	Queue lastQueue;
	Queue newQueue;
	@BeforeEach
	void setup() {
		newQueueId = UUID.randomUUID();
		queueId = UUID.randomUUID();
		ticketId = UUID.randomUUID();

		lastQueue = new Queue(queueId,
			   "TEST_AREA",
			   "TEST_SUBAREA",
			   UUID.randomUUID(),
			   LocalDateTime.now(),
			   LocalDateTime.now(),
			   UUID.randomUUID()
		);
		newQueue = new Queue(newQueueId,
			   "TEST_AREA_2",
			   "TEST_SUBAREA_2",
			   UUID.randomUUID(),
			   LocalDateTime.now(),
			   LocalDateTime.now(),
			   UUID.randomUUID()
		);
		ticket = new Ticket(ticketId,
			   TicketNumber.create(),
			   "TEST",
			   new ArrayList<>(),
			   lastQueue,
			   new ArrayList<>(),
			   LocalDateTime.now().plusDays(3),
			   TicketStateFactory.from(TicketStatus.PENDING),
			   UUID.randomUUID(),
			   LocalDateTime.now(),
			   UUID.randomUUID(),
			   LocalDateTime.now());
		cmd = new ChangeTicketQueue(ticketId, newQueueId);
	}

	@Test
	void deveRetornarErroPorNãoExistirTicket() {

		when(queueRepository.findById(newQueueId)).thenReturn(Optional.of(newQueue));
		when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

		var result = service.handle(cmd);

		assertTrue(result.hasErrors());

		verify(queueRepository, times(1)).findById(newQueueId);
		verifyNoMoreInteractions(queueRepository);

		verify(ticketRepository, times(1)).findById(ticketId);
		verifyNoMoreInteractions(ticketRepository);
	}
}
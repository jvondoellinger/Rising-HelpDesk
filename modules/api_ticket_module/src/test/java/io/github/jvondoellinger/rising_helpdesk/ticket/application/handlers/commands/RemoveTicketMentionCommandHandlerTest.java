package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.RemoveTicketMentionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.RemoveTicketMentionService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.MentionRepository;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveTicketMentionCommandHandlerTest implements UnitTest {

	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private MentionRepository mentionRepository;
	@Mock
	private CurrentUserService currentUserService;

	@InjectMocks
	private RemoveTicketMentionService service;

	@Captor
	private ArgumentCaptor<Mention> mentionCaptor;

	private RemoveTicketMentionCommand command;
	private UUID targetUserId;
	private UUID authorId;

	@BeforeEach
	void setup() {
		var queue = FakeEntityFactory.queue();
		var ticket = FakeEntityFactory.ticket(queue);
		targetUserId = UUID.randomUUID();
		authorId = UUID.randomUUID();
		command = new RemoveTicketMentionCommand(targetUserId, ticket.getId());
	}

	@Test
	@DisplayName("Deve concluir com sucesso quando o ticket não existir (fluxo atual do serviço)")
	void shouldSucceedAndSaveWhenTicketDoesNotExistPerServiceLogic() {
		when(ticketRepository.existsById(command.ticketId())).thenReturn(false);
		when(currentUserService.getUserId()).thenReturn(authorId);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(mentionRepository).save(mentionCaptor.capture());

		var mention = mentionCaptor.getValue();
		assertThat(mention.getUserId()).isEqualTo(command.userId());
		assertThat(mention.getTicketId()).isEqualTo(command.ticketId());
		assertThat(mention.getMentionedById()).isEqualTo(authorId);
	}

	@Test
	@DisplayName("Deve retornar erro quando o ticket existir (fluxo atual do serviço)")
	void shouldReturnErrorWhenTicketExistsPerServiceLogic() {
		when(ticketRepository.existsById(any(UUID.class))).thenReturn(true);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(mentionRepository, never()).save(any(Mention.class));
	}
}

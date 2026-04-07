package io.github.jvondoellinger.rising_helpdesk.ticket.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddInteractionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command.AddInteractionCommandService;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.application.handlers.helpers.FakeEntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddInteractionCommandHandlerTest implements UnitTest {
	@Mock
	private InteractionRepository repository;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private CurrentUserService currentUserService;

	@InjectMocks
	private AddInteractionCommandService service;
	@Captor
	private ArgumentCaptor<Interaction> argumentCaptor;

	private Ticket tk;
	private AddInteractionCommand command;

	@BeforeEach
	void setup() {
		var queue = FakeEntityFactory.queue();
		var tk = FakeEntityFactory.ticket(queue);
		this.tk = tk;

		command = new AddInteractionCommand(
			   FakeEntityFactory.fake_text,
			   tk.getId()
		);
	}

	@Test
	@DisplayName("Deve adicionar uma interação em um ticket quando existir o ticket")
	void shouldAddInteractionSuccessfully() {
		when(ticketRepository.findById(command.ticketId())).thenReturn(Optional.of(tk));
		when(currentUserService.getUserId()).thenReturn(UUID.randomUUID());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(argumentCaptor.capture());

		var interaction = argumentCaptor.getValue();

		Assertions.assertThat(interaction.getText()).isEqualTo(command.text());
		Assertions.assertThat(interaction.getTicketId()).isEqualTo(command.ticketId());
	}

	@Test
	@DisplayName("Deve retornar erro quando queue não é encontrada")
	void shouldReturnErrorWhenTicketNotFound() {
		when(ticketRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

		var result = service.handle(command);

		Assertions.assertThat(result.isSuccess()).isFalse();

		verify(ticketRepository, never()).save(any(Ticket.class));
	}
}

package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands.DeleteUserProfileService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserProfileServiceTest implements UnitTest {

	@Mock
	private UserProfileRepository repository;

	@InjectMocks
	private DeleteUserProfileService service;

	private UserProfile profile;
	private DeleteUserProfileCommand command;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		profile = FakeEntityFactory.userProfile(id, UUID.randomUUID());
		command = new DeleteUserProfileCommand(id);
	}

	@Test
	@DisplayName("Deve excluir usuário quando o id existir")
	void shouldExecuteSuccessfullyWhenUserProfileExists() {
		when(repository.findById(command.id())).thenReturn(Optional.of(profile));

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).delete(profile);
	}

	@Test
	@DisplayName("Deve retornar erro quando o usuário não for encontrado")
	void shouldReturnErrorWhenUserProfileNotFound() {
		when(repository.findById(command.id())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).delete(any(UserProfile.class));
	}

	@Test
	@DisplayName("Deve retornar erro quando o id for nulo")
	void shouldReturnErrorWhenIdIsNull() {
		var result = service.handle(new DeleteUserProfileCommand(null));

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).findById(any(UUID.class));
		verify(repository, never()).delete(any(UserProfile.class));
	}
}

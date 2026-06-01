package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.DeleteAccessProfileService;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteAccessProfileServiceTest implements UnitTest {

	@Mock
	private AccessProfileRepository repository;

	@InjectMocks
	private DeleteAccessProfileService service;

	private AccessProfile profile;
	private DeleteAccessProfileCommand command;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		profile = FakeEntityFactory.accessProfile(id, "PERFIL", List.of());
		command = new DeleteAccessProfileCommand(id);
	}

	@Test
	@DisplayName("Deve excluir perfil de acesso quando o id existir")
	void shouldExecuteSuccessfullyWhenAccessProfileExists() {
		when(repository.findById(command.accessProfileId())).thenReturn(Optional.of(profile));

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).delete(profile);
	}

	@Test
	@DisplayName("Deve retornar erro quando o perfil de acesso não for encontrado")
	void shouldReturnErrorWhenAccessProfileNotFound() {
		when(repository.findById(command.accessProfileId())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).delete(any(AccessProfile.class));
	}
}

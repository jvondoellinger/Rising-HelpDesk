package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.ChangeNameAccessProfileService;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.helpers.FakeEntityFactory;
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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeNameAccessProfileServiceTest implements UnitTest {

	@Mock
	private AccessProfileRepository repository;

	@InjectMocks
	private ChangeNameAccessProfileService service;

	@Captor
	private ArgumentCaptor<AccessProfile> accessProfileCaptor;

	private AccessProfile existing;
	private ChangeNameAccessProfileCommand command;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		existing = FakeEntityFactory.accessProfile(id, "NOME_ANTIGO", List.of());
		command = new ChangeNameAccessProfileCommand(id, "NOME_NOVO");
	}

	@Test
	@DisplayName("Deve alterar o nome quando o perfil existir e o novo nome estiver disponível")
	void shouldExecuteSuccessfullyWhenProfileExistsAndNewNameIsAvailable() {
		when(repository.findById(command.id())).thenReturn(Optional.of(existing));
		when(repository.existsByName(command.name())).thenReturn(false);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(accessProfileCaptor.capture());
		assertThat(accessProfileCaptor.getValue().getName()).isEqualTo(command.name());
		assertThat(accessProfileCaptor.getValue().getId()).isEqualTo(existing.getId());
	}

	@Test
	@DisplayName("Deve retornar erro quando o perfil não for encontrado")
	void shouldReturnErrorWhenAccessProfileNotFound() {
		when(repository.findById(command.id())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(AccessProfile.class));
	}
}

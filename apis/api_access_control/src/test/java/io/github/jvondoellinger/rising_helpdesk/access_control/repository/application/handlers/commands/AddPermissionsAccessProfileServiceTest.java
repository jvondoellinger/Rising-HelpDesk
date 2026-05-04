package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.AddPermissionsAccessProfileService;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
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
class AddPermissionsAccessProfileServiceTest implements UnitTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private PermissionMapper mapper;

	@InjectMocks
	private AddPermissionsAccessProfileService service;

	@Captor
	private ArgumentCaptor<AccessProfile> accessProfileCaptor;

	private AccessProfile profile;
	private PermissionsDTO permissionsDto;
	private AddPermissionsAccessProfileCommand command;
	private List<Permission> newPermissions;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		profile = FakeEntityFactory.accessProfile(id, "PERFIL", List.of());
		permissionsDto = new PermissionsDTO(List.of("TICKET::CREATE"));
		command = new AddPermissionsAccessProfileCommand(id, permissionsDto);
		newPermissions = List.of(FakeEntityFactory.permission(UUID.randomUUID(), "TICKET::CREATE"));
	}

	@Test
	@DisplayName("Deve adicionar permissões quando o perfil existir e elas ainda não estiverem todas concedidas")
	void shouldExecuteSuccessfullyWhenProfileExistsAndPermissionsAreNew() {
		when(repository.findById(command.id())).thenReturn(Optional.of(profile));
		when(mapper.from(permissionsDto)).thenReturn(newPermissions);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(accessProfileCaptor.capture());
		assertThat(accessProfileCaptor.getValue().getPermissions()).isEqualTo(newPermissions);
	}

	@Test
	@DisplayName("Deve retornar erro quando o perfil de acesso não for encontrado")
	void shouldReturnErrorWhenAccessProfileNotFound() {
		when(repository.findById(command.id())).thenReturn(Optional.empty());

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(AccessProfile.class));
	}
}

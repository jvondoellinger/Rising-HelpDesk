package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.RemovePermissionsAccessProfileService;
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
class RemovePermissionsAccessProfileServiceTest implements UnitTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private PermissionMapper mapper;

	@InjectMocks
	private RemovePermissionsAccessProfileService service;

	@Captor
	private ArgumentCaptor<AccessProfile> accessProfileCaptor;

	private AccessProfile profile;
	private PermissionsDTO permissionsDto;
	private RemovePermissionsAccessProfileCommand command;
	private List<Permission> mappedPermissions;

	@BeforeEach
	void setUp() {
		var profileId = UUID.randomUUID();
		var existingPerm = FakeEntityFactory.permission(UUID.randomUUID(), "USER::READ");
		profile = FakeEntityFactory.accessProfile(profileId, "PERFIL", List.of(existingPerm));
		permissionsDto = new PermissionsDTO(List.of("ROLE::WRITE"));
		command = new RemovePermissionsAccessProfileCommand(profileId, permissionsDto);
		mappedPermissions = List.of(FakeEntityFactory.permission(UUID.randomUUID(), "ROLE::WRITE"));
	}

	@Test
	@DisplayName("Deve processar remoção quando o perfil existir e as permissões informadas não estiverem todas já concedidas (regra atual do serviço)")
	void shouldExecuteSuccessfullyWhenProfileExistsAndRuleAllows() {
		when(repository.findById(command.id())).thenReturn(Optional.of(profile));
		when(mapper.from(permissionsDto)).thenReturn(mappedPermissions);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(accessProfileCaptor.capture());
		assertThat(accessProfileCaptor.getValue().getPermissions()).isEqualTo(mappedPermissions);
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

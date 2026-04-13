package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands.CreateAccessProfileService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.helpers.FakeEntityFactory;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccessProfileServiceTest implements UnitTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private PermissionRepository permissionRepository;
	@Mock
	private AccessProfileMapper mapper;

	@InjectMocks
	private CreateAccessProfileService service;

	@Captor
	private ArgumentCaptor<AccessProfile> accessProfileCaptor;

	private UUID permissionId;
	private CreateAccessProfileCommand command;
	private AccessProfile mappedProfile;

	@BeforeEach
	void setUp() {
		permissionId = UUID.randomUUID();
		command = new CreateAccessProfileCommand("PERFIL_TESTE", List.of(permissionId));
		mappedProfile = new AccessProfile(
			   command.name(),
			   List.of(FakeEntityFactory.permission(permissionId, "TICKET::READ"))
		);
	}

	@Test
	@DisplayName("Deve criar perfil de acesso quando o nome estiver livre e as permissões existirem")
	void shouldExecuteSuccessfullyWhenNameIsAvailableAndPermissionsExist() {
		when(repository.existsByName(command.name())).thenReturn(false);
		when(permissionRepository.existsById(permissionId)).thenReturn(true);
		when(mapper.from(command)).thenReturn(mappedProfile);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(accessProfileCaptor.capture());
		assertThat(accessProfileCaptor.getValue().getName()).isEqualTo(command.name());
	}

	@Test
	@DisplayName("Deve retornar erro quando já existir perfil com o mesmo nome")
	void shouldReturnErrorWhenAccessProfileNameAlreadyExists() {
		when(repository.existsByName(command.name())).thenReturn(true);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isFalse();
		verify(repository, never()).save(any(AccessProfile.class));
		verify(permissionRepository, never()).existsById(any(UUID.class));
	}
}

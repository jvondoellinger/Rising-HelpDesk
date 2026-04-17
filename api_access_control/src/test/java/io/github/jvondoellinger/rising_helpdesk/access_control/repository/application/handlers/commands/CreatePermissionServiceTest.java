package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.permissions.CreatePermissionService;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePermissionServiceTest implements UnitTest {

	@Mock
	private PermissionRepository repository;

	@InjectMocks
	private CreatePermissionService service;

	@Captor
	private ArgumentCaptor<Permission> permissionCaptor;

	private CreatePermissionCommand command;

	@BeforeEach
	void setUp() {
		command = new CreatePermissionCommand("MODULE::ACTION");
	}

	@Test
	@DisplayName("Deve persistir permissão quando o código estiver no formato válido")
	void shouldExecuteSuccessfullyWhenCommandCodeIsValid() {
		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(permissionCaptor.capture());
		assertThat(permissionCaptor.getValue().getCode()).isEqualTo("MODULE::ACTION");
	}

	@Test
	@DisplayName("Deve falhar sem persistir quando o código da permissão for inválido")
	void shouldReturnErrorWhenPermissionCodeIsInvalid() {
		var invalid = new CreatePermissionCommand("FORMATO_INVALIDO");

		assertThatThrownBy(() -> service.handle(invalid))
			   .isInstanceOf(RuntimeException.class);

		verify(repository, never()).save(any(Permission.class));
	}
}

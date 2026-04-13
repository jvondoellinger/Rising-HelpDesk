package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.permissions.FindPermissionByCodeService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPermissionByCodeServiceTest {

	@Mock
	private PermissionRepository repository;
	@Mock
	private PermissionMapper mapper;

	@InjectMocks
	private FindPermissionByCodeService service;

	private FindPermissionByCodeQuery query;
	private Permission permission;
	private PermissionDetails details;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		query = new FindPermissionByCodeQuery("TICKET::READ");
		permission = FakeEntityFactory.permission(id, query.code());
		details = new PermissionDetails(id, permission.getCode(), permission.getCreatedAt());
	}

	@Test
	@DisplayName("Deve retornar permissão quando o código existir")
	void shouldReturnPermissionWhenCodeExists() {
		when(repository.findByCode(query.code())).thenReturn(Optional.of(permission));
		when(mapper.details(permission)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().code()).isEqualTo(query.code());
	}

	@Test
	@DisplayName("Deve retornar erro quando o código não existir")
	void shouldFailWhenCodeNotFound() {
		when(repository.findByCode(query.code())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("code");
	}
}

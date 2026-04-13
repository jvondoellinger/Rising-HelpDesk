package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.permissions.FindPermissionPaginationService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.helpers.FakeEntityFactory;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPermissionPaginationServiceTest {

	@Mock
	private PermissionRepository repository;
	@Mock
	private PermissionMapper mapper;

	@InjectMocks
	private FindPermissionPaginationService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindPermissionPaginationQuery query;
	private Pagination<Permission> domainPage;

	@BeforeEach
	void setUp() {
		query = new FindPermissionPaginationQuery(2, 15);
		var id = UUID.randomUUID();
		var perm = FakeEntityFactory.permission(id, "TICKET::READ");
		domainPage = Pagination.of(List.of(perm), 2, 1);
	}

	@Test
	@DisplayName("Deve retornar permissões paginadas e repassar page e size corretos ao repositório")
	void shouldReturnPaginatedPermissions() {
		when(repository.findByPagination(any(PaginationFilter.class))).thenReturn(domainPage);
		when(mapper.details(domainPage)).thenReturn(Pagination.of(List.of(), query.page(), domainPage.totalPages()));

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).findByPagination(filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

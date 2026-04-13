package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.accessprofile.FindAccessProfilePaginationService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
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
class FindAccessProfilePaginationServiceTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private AccessProfileMapper mapper;

	@InjectMocks
	private FindAccessProfilePaginationService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindAccessProfilePaginationQuery validQuery;
	private Pagination<AccessProfile> domainPage;

	@BeforeEach
	void setUp() {
		validQuery = new FindAccessProfilePaginationQuery(1, 20);
		var id = UUID.randomUUID();
		var profile = FakeEntityFactory.accessProfile(id, "P", List.of());
		domainPage = Pagination.of(List.of(profile), 1, 1);
	}

	@Test
	@DisplayName("Deve retornar erro quando page ou size forem negativos")
	void shouldFailWhenPageOrSizeInvalid() {
		var badPage = new FindAccessProfilePaginationQuery(-1, 10);
		var badSize = new FindAccessProfilePaginationQuery(0, -1);

		assertThat(service.handle(badPage).isSuccess()).isFalse();
		assertThat(service.handle(badSize).isSuccess()).isFalse();
	}

	@Test
	@DisplayName("Deve paginar perfis de acesso com page e size corretos")
	void shouldPaginateAccessProfiles() {
		when(repository.findByPagination(any(PaginationFilter.class))).thenReturn(domainPage);
		when(mapper.detailsPagination(domainPage)).thenReturn(Pagination.of(List.of(), validQuery.page(), domainPage.totalPages()));

		var result = service.handle(validQuery);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).findByPagination(filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(validQuery.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(validQuery.size());
	}
}

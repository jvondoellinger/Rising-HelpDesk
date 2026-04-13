package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.userprofile.FindUserProfilePaginationService;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
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
class FindUserProfilePaginationServiceTest {

	@Mock
	private UserProfileRepository repository;
	@Mock
	private UserProfileMapper mapper;

	@InjectMocks
	private FindUserProfilePaginationService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindUserProfilePaginationQuery query;
	private Pagination<UserProfile> domainPage;

	@BeforeEach
	void setUp() {
		query = new FindUserProfilePaginationQuery(3, 25);
		var userId = UUID.randomUUID();
		var accessProfileId = UUID.randomUUID();
		domainPage = Pagination.of(List.of(new UserProfile(userId, accessProfileId)), 3, 1);
	}

	@Test
	@DisplayName("Deve retornar user profiles paginados com filtro page/size corretos")
	void shouldPaginateUserProfiles() {
		when(repository.findByPagination(any(PaginationFilter.class))).thenReturn(domainPage);
		when(mapper.detailsPagination(domainPage)).thenReturn(Pagination.of(List.of(), query.page(), domainPage.totalPages()));

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).findByPagination(filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

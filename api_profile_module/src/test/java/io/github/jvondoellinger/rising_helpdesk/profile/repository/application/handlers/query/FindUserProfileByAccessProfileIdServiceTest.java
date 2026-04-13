package io.github.jvondoellinger.rising_helpdesk.profile.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.userprofile.FindUserProfileByAccessProfileIdService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserProfileByAccessProfileIdServiceTest {

	@Mock
	private UserProfileRepository repository;
	@Mock
	private UserProfileMapper mapper;

	@InjectMocks
	private FindUserProfileByAccessProfileIdService service;

	@Captor
	private ArgumentCaptor<PaginationFilter> filterCaptor;

	private FindUserProfileByAccessProfileIdQuery query;
	private UUID accessProfileId;

	@BeforeEach
	void setUp() {
		accessProfileId = UUID.randomUUID();
		query = new FindUserProfileByAccessProfileIdQuery(accessProfileId, 0, 10);
	}

	@Test
	@DisplayName("Deve retornar erro quando não houver perfis para o access profile")
	void shouldFailWhenEmpty() {
		var empty = Pagination.<UserProfile>empty();
		when(repository.findByAccessProfileId(eq(accessProfileId), any(PaginationFilter.class))).thenReturn(empty);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("profile");
	}

	@Test
	@DisplayName("Deve retornar paginação quando existir perfil vinculado ao access profile")
	void shouldReturnPaginationWhenDataExists() {
		var userId = UUID.randomUUID();
		var userProfile = new UserProfile(userId, accessProfileId);
		var page = Pagination.of(List.of(userProfile), 0, 1);
		var mapped = Pagination.of(
			   List.of(new UserProfileDetails(userId, accessProfileId, userProfile.getCreatedAt(), userProfile.getUpdatedAt())),
			   0,
			   1
		);

		when(repository.findByAccessProfileId(eq(accessProfileId), any(PaginationFilter.class))).thenReturn(page);
		when(mapper.detailsPagination(page)).thenReturn(mapped);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().items()).hasSize(1);
		verify(repository).findByAccessProfileId(eq(accessProfileId), filterCaptor.capture());
		assertThat(filterCaptor.getValue().page()).isEqualTo(query.page());
		assertThat(filterCaptor.getValue().size()).isEqualTo(query.size());
	}
}

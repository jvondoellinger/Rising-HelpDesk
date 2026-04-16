package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.userprofile.FindUserProfileByUserIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.services.query.userprofile.FindUserProfileByUserIdService;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository.UserProfileRepository;
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
class FindUserProfileByUserIdServiceTest {

	@Mock
	private UserProfileRepository repository;
	@Mock
	private UserProfileMapper mapper;

	@InjectMocks
	private FindUserProfileByUserIdService service;

	private FindUserProfileByUserIdQuery query;
	private UserProfile userProfile;
	private UserProfileDetails details;

	@BeforeEach
	void setUp() {
		var userId = UUID.randomUUID();
		var accessProfileId = UUID.randomUUID();
		query = new FindUserProfileByUserIdQuery(userId);
		userProfile = new UserProfile(userId, accessProfileId);
		details = new UserProfileDetails(userId, accessProfileId, userProfile.getCreatedAt(), userProfile.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve retornar detalhes quando existir perfil com o user id")
	void shouldReturnDetailsWhenFound() {
		when(repository.findById(query.userId())).thenReturn(Optional.of(userProfile));
		when(mapper.details(userProfile)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().userId()).isEqualTo(query.userId());
	}

	@Test
	@DisplayName("Deve retornar erro quando não existir perfil para o usuário")
	void shouldFailWhenNotFound() {
		when(repository.findById(query.userId())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("User Profile");
	}
}

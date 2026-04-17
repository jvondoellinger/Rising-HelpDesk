package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile.FindAccessProfileByIdQueryService;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAccessProfileByIdQueryServiceTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private AccessProfileMapper mapper;

	@InjectMocks
	private FindAccessProfileByIdQueryService service;

	private FindAccessProfileByIdQuery query;
	private AccessProfile profile;
	private AccessProfileDetails details;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		query = new FindAccessProfileByIdQuery(id);
		profile = FakeEntityFactory.accessProfile(id, "PERFIL", List.of());
		details = new AccessProfileDetails(id, List.of(), profile.getCreatedAt(), profile.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve retornar detalhes quando o id existir")
	void shouldReturnDetailsWhenFound() {
		when(repository.findById(query.id())).thenReturn(Optional.of(profile));
		when(mapper.details(profile)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().accessProfileId()).isEqualTo(query.id());
	}

	@Test
	@DisplayName("Deve retornar erro quando o perfil não for encontrado")
	void shouldFailWhenNotFound() {
		when(repository.findById(query.id())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError()).containsIgnoringCase("Access Profile");
	}
}

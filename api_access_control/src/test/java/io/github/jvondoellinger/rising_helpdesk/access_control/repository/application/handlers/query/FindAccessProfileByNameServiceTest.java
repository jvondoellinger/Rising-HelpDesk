package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile.FindAccessProfileByNameService;
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
class FindAccessProfileByNameServiceTest {

	@Mock
	private AccessProfileRepository repository;
	@Mock
	private AccessProfileMapper mapper;

	@InjectMocks
	private FindAccessProfileByNameService service;

	private FindAccessProfileByNameQuery query;
	private AccessProfile profile;
	private AccessProfileDetails details;

	@BeforeEach
	void setUp() {
		var id = UUID.randomUUID();
		query = new FindAccessProfileByNameQuery("ADMIN");
		profile = FakeEntityFactory.accessProfile(id, "ADMIN", List.of());
		details = new AccessProfileDetails(id, List.of(), profile.getCreatedAt(), profile.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve retornar detalhes quando existir perfil com o nome informado")
	void shouldReturnDetailsWhenProfileExists() {
		when(repository.findByName(query.name())).thenReturn(Optional.of(profile));
		when(mapper.details(profile)).thenReturn(details);

		var result = service.handle(query);

		assertThat(result.isSuccess()).isTrue();
		assertThat(result.getValue().accessProfileId()).isEqualTo(details.accessProfileId());
	}

	@Test
	@DisplayName("Deve retornar erro quando não existir perfil com o nome informado")
	void shouldFailWhenProfileNotFound() {
		when(repository.findByName(query.name())).thenReturn(Optional.empty());

		var result = service.handle(query);

		assertThat(result.isSuccess()).isFalse();
		assertThat(result.getError().description()).containsIgnoringCase("Access Profile");
	}
}

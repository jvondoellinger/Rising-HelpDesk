package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.userprofile.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.services.commands.CreateUserProfileService;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.helpers.FakeEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserProfileServiceTest implements UnitTest {

	@Mock
	private UserProfileRepository repository;
	@Mock
	private UserProfileMapper mapper;

	@InjectMocks
	private CreateUserProfileService service;

	@Captor
	private ArgumentCaptor<UserProfile> userProfileCaptor;

	private CreateUserProfileCommand command;
	private UserProfile mapped;

	@BeforeEach
	void setUp() {
		var userId = UUID.randomUUID();
		var accessProfileId = UUID.randomUUID();
		command = new CreateUserProfileCommand(userId, accessProfileId);
		mapped = FakeEntityFactory.userProfile(userId, accessProfileId);
	}

	@Test
	@DisplayName("Deve criar vínculo de usuário ao perfil quando o comando for válido")
	void shouldExecuteSuccessfullyWhenCommandIsValid() {
		when(mapper.from(command)).thenReturn(mapped);

		var result = service.handle(command);

		assertThat(result.isSuccess()).isTrue();
		verify(repository).save(userProfileCaptor.capture());
		assertThat(userProfileCaptor.getValue().getUserId()).isEqualTo(command.userId());
		assertThat(userProfileCaptor.getValue().getAccessProfile()).isEqualTo(command.accessProfileId());
	}

	@Test
	@DisplayName("Deve propagar falha e não persistir quando o mapper lançar exceção")
	void shouldReturnErrorWhenMapperFails() {
		when(mapper.from(any(CreateUserProfileCommand.class))).thenThrow(new IllegalStateException("falha de mapeamento"));

		assertThatThrownBy(() -> service.handle(command))
			   .isInstanceOf(IllegalStateException.class)
			   .hasMessageContaining("falha de mapeamento");

		verify(repository, never()).save(any(UserProfile.class));
	}
}

package io.github.jvondoellinger.rising_helpdesk.profile.application.services;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.useCases.CreateUserProfileUseCase;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserProfileService implements CreateUserProfileUseCase {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public UserProfileDetails execute(CreateUserProfileCommand command) {
		var entity = mapper.from(command);
		var result = repository.save(entity);

		return mapper.details(result);
	}
}

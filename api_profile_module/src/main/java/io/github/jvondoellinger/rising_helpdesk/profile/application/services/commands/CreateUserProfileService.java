package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.CreateUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserProfileService implements CreateUserProfileHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public Result<Void> handle(CreateUserProfileCommand cmd) {
		var entity = mapper.from(cmd);
		var result = repository.save(entity);

		return new Result.Success<>(null);
	}

	@Override
	public Class<CreateUserProfileCommand> getType() {
		return CreateUserProfileCommand.class;
	}
}

package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.userprofile.CreateUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserProfileService implements CreateUserProfileHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public ResultTransformerStep<Void> handle(CreateUserProfileCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
			var entity = mapper.from(cmd);
			repository.save(entity);

			return Result.success();
		});
	}

	@Override
	public Class<CreateUserProfileCommand> getCommandType() {
		return CreateUserProfileCommand.class;
	}
}

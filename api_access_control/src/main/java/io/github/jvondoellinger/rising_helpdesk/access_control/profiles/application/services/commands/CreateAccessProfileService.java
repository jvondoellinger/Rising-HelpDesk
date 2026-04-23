package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionRepository permissionRepository;
	private final AccessProfileMapper mapper;

	@Override
	public Result<Void, String> handle(CreateAccessProfileCommand cmd) {
		var alreadyExists = repository.existsByName(cmd.name());

		if (alreadyExists) {
			return Result.failure("Access Profile already registered.");
		}

		var match = cmd
			   .permissions()
			   .stream()
			   .allMatch(permissionRepository::existsById);

		if (!match) {
			return Result.failure("One or more permissions were not registered.");
		}

		var accessprofile = mapper.from(cmd);
		repository.save(accessprofile);

		return Result.success();

	}

	@Override
	public Class<CreateAccessProfileCommand> getCommandType() {
		return CreateAccessProfileCommand.class;
	}
}
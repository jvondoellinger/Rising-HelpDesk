package io.github.jvondoellinger.rising_helpdesk.access_control.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.accessprofile.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.commands.accessprofile.ChangeNameAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeNameAccessProfileService implements ChangeNameAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<Void> handle(ChangeNameAccessProfileCommand cmd) {
		var optional = repository.findById(cmd.id());

		if (optional.isEmpty()) {
			return Result.failure("No access found on persistence.");
		}

		if (repository.existsByName(cmd.name())) {
			return Result.failure("Already exists a profile with this name!");
		}

		var accessprofile = optional.get();
		var updated = new AccessProfile(
			   accessprofile.getId(),
			   cmd.name() ,
			   accessprofile.getPermissions(),
			   accessprofile.getCreatedAt(),
			   LocalDateTime.now()
		);

		repository.save(updated);

		return Result.success();
	}

	@Override
	public Class<ChangeNameAccessProfileCommand> getCommandType() {
		return ChangeNameAccessProfileCommand.class;
	}
}
package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.DeleteAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccessProfileService implements DeleteAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<Void> handle(DeleteAccessProfileCommand cmd) {
		var optional = repository.findById(cmd.accessProfileId());

		if (optional.isEmpty()) {
			return Result.failure("ID not found");
		}

		var accessprofile = optional.get();

		repository.delete(accessprofile);

		return Result.success();
	}

	@Override
	public Class<DeleteAccessProfileCommand> getCommandType() {
		return DeleteAccessProfileCommand.class;
	}
}

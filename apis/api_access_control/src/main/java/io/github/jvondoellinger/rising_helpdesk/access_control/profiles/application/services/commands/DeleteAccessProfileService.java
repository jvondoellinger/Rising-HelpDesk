package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.DeleteAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class DeleteAccessProfileService implements DeleteAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public ResultB<Void> handle(DeleteAccessProfileCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.accessProfileId());

				   if (optional.isEmpty())
					   return ResultB.error(new DomainError("ID_NOT_FOUND", "ID not found"));

				   var accessprofile = optional.get();
				   repository.delete(accessprofile);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<DeleteAccessProfileCommand> getCommandType() {
		return DeleteAccessProfileCommand.class;
	}
}

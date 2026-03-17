package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.DeleteAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccessProfileService implements DeleteAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<Void> handle(DeleteAccessProfileCommand cmd) {
		var accessProfile = repository.queryById(cmd.accessProfileId());

		if (accessProfile == null) {
			return new Result.Failure<>(new KernelException("ID not found"));
		}

		// Salva no banco de dados
		repository.delete(accessProfile);

		return new Result.Success<>(null);
	}

	@Override
	public Class<DeleteAccessProfileCommand> getCommandType() {
		return DeleteAccessProfileCommand.class;
	}
}

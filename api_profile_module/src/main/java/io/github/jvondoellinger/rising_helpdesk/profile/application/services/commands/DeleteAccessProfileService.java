package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.DeleteAccessProfileCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccessProfileService implements DeleteAccessProfileCommandHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<Void> handle(DeleteAccessProfileCommand request) {
		var accessProfile = repository.queryById(request.accessProfileId());

		if (accessProfile == null) {
			return new Result.Failure<>(new KernelException("ID not found"));
		}

		// Salva no banco de dados
		repository.delete(accessProfile);

		return new Result.Success<>(null);
	}
}

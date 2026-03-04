package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public Result<Void> handle(CreateAccessProfileCommand command) {
		var alreadyExists = repository.existsByName(command.name());

		if (alreadyExists) {
			return new Result.Failure<>(KernelException.conflict("Access Profile already registered."));
		}

		var ap = mapper.from(command);
		var result = repository.save(ap);
		var mapped= mapper.details(result);

		return new Result.Success<Void>(null);
	}
}

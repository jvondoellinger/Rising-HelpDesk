package io.github.jvondoellinger.rising_helpdesk.profile.application.services;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.useCases.CreateAccessProfileUseCase;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileUseCase {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;
	@Override
	public CommandResult<AccessProfileDetails> execute(CreateAccessProfileCommand command) {
		var alreadyExists = repository.existsByName(command.name());

		if (alreadyExists) {
			return new CommandResult.Failure<>(KernelException.conflict("Access Profile already registered."));
		}

		var ap = mapper.from(command);
		var result = repository.save(ap);
		var mapped= mapper.details(result);

		return new CommandResult.Success<>(mapped);
	}
}

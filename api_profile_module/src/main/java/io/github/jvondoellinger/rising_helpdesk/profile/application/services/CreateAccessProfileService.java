package io.github.jvondoellinger.rising_helpdesk.profile.application.services;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.useCases.CreateAccessProfileUseCase;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileUseCase {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;
	@Override
	public AccessProfileDetails execute(CreateAccessProfileCommand command) {
		var ap = mapper.from(command);
		var result = repository.save(ap);

		return mapper.details(result);
	}
}

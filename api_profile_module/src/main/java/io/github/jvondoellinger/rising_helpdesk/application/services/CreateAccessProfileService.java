package io.github.jvondoellinger.rising_helpdesk.application.services;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.application.useCases.CreateAccessProfileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileUseCase {
	@Override
	public AccessProfileDetails execute(CreateAccessProfileCommand request) {
		return null;
	}
}

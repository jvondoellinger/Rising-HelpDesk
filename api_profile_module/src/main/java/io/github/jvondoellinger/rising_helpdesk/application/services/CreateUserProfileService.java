package io.github.jvondoellinger.rising_helpdesk.application.services;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.application.useCases.CreateUserProfileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserProfileService implements CreateUserProfileUseCase {
	@Override
	public UserProfileDetails execute(CreateUserProfileCommand request) {
		return null;
	}
}

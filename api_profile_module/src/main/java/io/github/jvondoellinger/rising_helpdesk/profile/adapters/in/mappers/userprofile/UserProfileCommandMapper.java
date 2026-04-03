package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.CreateUserProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile.CreateUserProfileCommand;
import org.springframework.stereotype.Service;

@Service
public final class UserProfileCommandMapper {
	public CreateUserProfileCommand toCommand(CreateUserProfileRequest request) {
		return new CreateUserProfileCommand(
			   request.userId(),
			   request.accessProfileId()
		);
	}
}

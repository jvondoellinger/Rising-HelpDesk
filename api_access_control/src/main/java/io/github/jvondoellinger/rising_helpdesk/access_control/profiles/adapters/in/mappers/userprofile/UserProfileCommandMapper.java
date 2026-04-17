package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreateUserProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.CreateUserProfileCommand;
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

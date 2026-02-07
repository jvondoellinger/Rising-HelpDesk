package io.github.jvondoellinger.rising_helpdesk.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.domain.AccessProfile;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileMapper {
	public AccessProfile from(CreateAccessProfileCommand command) {
		return new AccessProfile(command.name(), command.permissions());
	}

	public AccessProfileDetails details(AccessProfile accessProfile) {
		return new AccessProfileDetails(accessProfile.getId(), accessProfile.getCreatedAt(), accessProfile.getUpdatedAt());
	}
}

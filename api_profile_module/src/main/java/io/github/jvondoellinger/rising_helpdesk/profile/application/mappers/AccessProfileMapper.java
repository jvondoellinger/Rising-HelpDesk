package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileMapper {
	public AccessProfile from(CreateAccessProfileCommand command) {
		return new AccessProfile(command.name(), command.permissions());
	}

	public AccessProfileDetails details(AccessProfile accessProfile) {
		return new AccessProfileDetails(accessProfile.getId(), accessProfile.getPermissions(), accessProfile.getCreatedAt(), accessProfile.getUpdatedAt());
	}
}

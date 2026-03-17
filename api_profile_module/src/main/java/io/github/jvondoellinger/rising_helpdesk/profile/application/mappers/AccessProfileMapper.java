package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileMapper {
	public AccessProfile from(CreateAccessProfileCommand command) {
		var permissions = command.permissions().stream().map(x -> new Permission(x, null, null)).toList();

		return new AccessProfile(command.name(), permissions);
	}

	public AccessProfileDetails details(AccessProfile accessProfile) {
		return new AccessProfileDetails(accessProfile.getId(), accessProfile.getPermissions(), accessProfile.getCreatedAt(), accessProfile.getUpdatedAt());
	}
}

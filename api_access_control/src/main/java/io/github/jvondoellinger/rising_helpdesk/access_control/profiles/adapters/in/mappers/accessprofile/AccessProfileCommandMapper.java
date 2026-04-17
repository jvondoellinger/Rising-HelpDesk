package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.ChangeAccessProfileNameRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.CreateAccessProfileCommand;
import org.springframework.stereotype.Service;

@Service
public final class AccessProfileCommandMapper {
	public CreateAccessProfileCommand from(CreateAccessProfileRequest request) {
		return new CreateAccessProfileCommand(
			   request.name(),
			   request.permissionIds()
		);
	}

	public ChangeNameAccessProfileCommand from(ChangeAccessProfileNameRequest request) {
		return new ChangeNameAccessProfileCommand(
			   request.id(),
			   request.name()
		);
	}
}

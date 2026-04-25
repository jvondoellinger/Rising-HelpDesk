package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.RemovePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


// Remover a duplicidade do mapeamento de perissionsDTO para Permissions
@FixAfter
@Service
@AllArgsConstructor
public class RemovePermissionsAccessProfileService implements RemovePermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	public ResultV1<Void, String> handle(RemovePermissionsAccessProfileCommand cmd) {
		var optional = repository.findById(cmd.id());

		if (optional.isEmpty()) {
			return ResultV1.failure("No access found on persistence.");
		}

		var accessprofile = optional.get();
		var permissions = mapper.from(cmd.permissions());

		if (accessprofile.hasAllPermissions(permissions)) {
			return ResultV1.failure("Permissions already granted.");
		}

		var newValue = new AccessProfile(
			   accessprofile.getId(),
			   accessprofile.getName() ,
			   permissions,
			   accessprofile.getCreatedAt(),
			   LocalDateTime.now()
		);

		repository.save(newValue);

		return ResultV1.success();
	}

	@Override
	public Class<RemovePermissionsAccessProfileCommand> getCommandType() {
		return RemovePermissionsAccessProfileCommand.class;
	}
}

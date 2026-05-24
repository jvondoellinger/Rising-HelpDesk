package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.RemovePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;


// Remover a duplicidade do mapeamento de perissionsDTO para Permissions
@FixAfter
@Service
@AllArgsConstructor
public class RemovePermissionsAccessProfileService implements RemovePermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	public ResultB<Void> handle(RemovePermissionsAccessProfileCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.id());

				   if (optional.isEmpty())
					   return ResultB.error(new DomainError("NO_ACCESS_FOUND_ON_PERSISTENCE", "No access found on persistence."));

				   var accessprofile = optional.get();
				   var permissions = mapper.from(cmd.permissions());

				   if (accessprofile.hasAllPermissions(permissions))
					   return (ResultB<Void>)(ResultB<?>)ResultB.create().map(v -> new DomainError("PERMISSIONS_ALREADY_GRANTED", "Permissions already granted."));

				   var newValue = new AccessProfile(
						 accessprofile.getId(),
						 accessprofile.getName(),
						 permissions,
						 accessprofile.getCreatedAt(),
						 LocalDateTime.now()
				   );

				   repository.save(newValue);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<RemovePermissionsAccessProfileCommand> getCommandType() {
		return RemovePermissionsAccessProfileCommand.class;
	}
}

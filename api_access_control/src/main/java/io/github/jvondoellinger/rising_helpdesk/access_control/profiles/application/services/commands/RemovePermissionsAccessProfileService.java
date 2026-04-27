package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.RemovePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;


// Remover a duplicidade do mapeamento de perissionsDTO para Permissions
@FixAfter
@Service
@AllArgsConstructor
public class RemovePermissionsAccessProfileService implements RemovePermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	public ResultTransformerStep<Void> handle(RemovePermissionsAccessProfileCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.id());

				   if (optional.isEmpty())
					   return Result.error(new DomainError("NO_ACCESS_FOUND_ON_PERSISTENCE", "No access found on persistence."));

				   var accessprofile = optional.get();
				   var permissions = mapper.from(cmd.permissions());

				   if (accessprofile.hasAllPermissions(permissions))
					   return Result.error(new DomainError("PERMISSIONS_ALREADY_GRANTED", "Permissions already granted."));

				   var newValue = new AccessProfile(
						 accessprofile.getId(),
						 accessprofile.getName(),
						 permissions,
						 accessprofile.getCreatedAt(),
						 LocalDateTime.now()
				   );

				   repository.save(newValue);

				   return Result.success();
			   });
	}

	@Override
	public Class<RemovePermissionsAccessProfileCommand> getCommandType() {
		return RemovePermissionsAccessProfileCommand.class;
	}
}

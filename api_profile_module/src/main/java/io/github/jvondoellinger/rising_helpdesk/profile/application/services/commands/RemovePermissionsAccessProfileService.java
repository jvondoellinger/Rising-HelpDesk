package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.RemovePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RemovePermissionsAccessProfileService implements RemovePermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	public Result<Void> handle(RemovePermissionsAccessProfileCommand cmd) {
		var persistedProfile = repository.queryById(cmd.id());

		if (persistedProfile == null) {
			return new Result.Failure<>(new KernelException("No access found on persistence."));
		}

		var permissions = cmd.permissions().values().stream().map(Permission::of).toList();

		if (persistedProfile.hasAllPermissions(permissions)) {
			return new Result.Failure<>(new KernelException("Permissions already granted."));
		}

		var newValue = new AccessProfile(
			   persistedProfile.getId(),
			   persistedProfile.getName() ,
			   permissions,
			   persistedProfile.getCreatedAt(),
			   LocalDateTime.now()
		);
		repository.save(newValue); // Atualiza no banco de dados

		return new Result.Success<>(null);
	}

	@Override
	public Class<RemovePermissionsAccessProfileCommand> getCommandType() {
		return RemovePermissionsAccessProfileCommand.class;
	}
}

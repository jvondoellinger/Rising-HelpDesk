package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.AddPermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AddPermissionsAccessProfileService implements AddPermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	@FixAfter
	// As permissoes devem ser validadas no repositorio
	public Result<Void> handle(AddPermissionsAccessProfileCommand cmd) {
		var persistedProfile = repository.queryById(cmd.id());

		if (persistedProfile == null) {
			return new Result.Failure<>(new KernelException("No access found on persistence."));
		}

		var permissions = cmd.permissions()
				.values()
				.stream()
				.map(Permission::of)
				.toList();

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
	public Class<AddPermissionsAccessProfileCommand> getCommandType() {
		return AddPermissionsAccessProfileCommand.class;
	}
}

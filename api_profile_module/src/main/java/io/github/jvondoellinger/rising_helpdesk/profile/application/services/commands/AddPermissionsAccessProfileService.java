package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.AddPermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@FixAfter // reduzir codigo duplicado na hora de atualizar as permissoes!
@Service
@AllArgsConstructor
public class AddPermissionsAccessProfileService implements AddPermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	@FixAfter
	// As permissoes devem ser validadas no repositorio
	public Result<Void> handle(AddPermissionsAccessProfileCommand cmd) {
		var optional = repository.findById(cmd.id());

		if (optional.isEmpty()) {
			return Result.failure("No access found on persistence.");
		}

		var accessprofile = optional.get();
		var permissions = mapper.from(cmd.permissions());

		if (accessprofile.hasAllPermissions(permissions)) {
			return Result.failure("Permissions already granted.");
		}

		var newValue = new AccessProfile(
			   accessprofile.getId(),
			   accessprofile.getName(),
			   permissions,
			   accessprofile.getCreatedAt(),
			   LocalDateTime.now()
		);

		repository.save(newValue); // Atualiza no banco de dados

		return Result.success();
	}

	@Override
	public Class<AddPermissionsAccessProfileCommand> getCommandType() {
		return AddPermissionsAccessProfileCommand.class;
	}
}

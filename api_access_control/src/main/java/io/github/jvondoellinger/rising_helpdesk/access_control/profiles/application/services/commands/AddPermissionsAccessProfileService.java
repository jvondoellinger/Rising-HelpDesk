package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.AddPermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
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
	public ResultV1<Void, String> handle(AddPermissionsAccessProfileCommand cmd) {
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
			   accessprofile.getName(),
			   permissions,
			   accessprofile.getCreatedAt(),
			   LocalDateTime.now()
		);

		repository.save(newValue); // Atualiza no banco de dados

		return ResultV1.success();
	}

	@Override
	public Class<AddPermissionsAccessProfileCommand> getCommandType() {
		return AddPermissionsAccessProfileCommand.class;
	}
}

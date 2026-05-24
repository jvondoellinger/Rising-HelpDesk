package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.permission.CreatePermissionHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePermissionService implements CreatePermissionHandler {
	private final PermissionRepository repository;

	@Override
	public ResultB<Void> handle(CreatePermissionCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var permission = Permission.of(cmd.command());
				   repository.save(permission);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<CreatePermissionCommand> getCommandType() {
		return CreatePermissionCommand.class;
	}
}

package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.permission.CreatePermissionHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePermissionService implements CreatePermissionHandler {
    private final PermissionRepository repository;

    @Override
    public Result<Void> handle(CreatePermissionCommand cmd) {
        var e = Permission.of(cmd.command());

        repository.save(e);

        return Result.success();
    }

    @Override
    public Class<CreatePermissionCommand> getCommandType() {
        return CreatePermissionCommand.class;
    }
}

package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.permission.CreatePermissionHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
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

        return new Result.Success<>(null);
    }

    @Override
    public Class<CreatePermissionCommand> getCommandType() {
        return CreatePermissionCommand.class;
    }
}

package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.userprofile.DeleteUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserProfileService implements DeleteUserProfileHandler {
    private final UserProfileRepository repository;

    @Override
    public ResultV1<Void, String> handle(DeleteUserProfileCommand cmd) {
        var id = cmd.id();

        if (id == null) {
            return ResultV1.failure("ID is blank.");
        }

        var optional = repository.findById(id);

        if (optional.isEmpty()) {
            return ResultV1.failure("User does not exist!");
        }

        var userprofile = optional.get();

        repository.delete(userprofile);

        return ResultV1.success();
    }

    @Override
    public Class<DeleteUserProfileCommand> getCommandType() {
        return DeleteUserProfileCommand.class;
    }
}

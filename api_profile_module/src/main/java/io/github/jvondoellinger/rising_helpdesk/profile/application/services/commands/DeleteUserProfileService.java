package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.userprofile.DeleteUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserProfileService implements DeleteUserProfileHandler {
    private final UserProfileRepository repository;

    @Override
    public Result<Void> handle(DeleteUserProfileCommand cmd) {
        var id = cmd.id();

        if (id == null) {
            return new Result.Failure<>("ID is blank.");
        }

        var optional = repository.findById(id);

        if (optional.isEmpty()) {
            return new Result.Failure<>("User does not exist!");
        }

        var userprofile = optional.get();

        repository.delete(userprofile);

        return new Result.Success<>(null);
    }

    @Override
    public Class<DeleteUserProfileCommand> getCommandType() {
        return DeleteUserProfileCommand.class;
    }
}

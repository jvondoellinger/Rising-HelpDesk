package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.DeleteUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfileRepository;
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
            return new Result.Failure<>(new KernelException("ID is blank."));
        }

        var persistedUser = repository.queryById(id);

        if (persistedUser == null) {
            return new Result.Failure<>(new KernelException("User does not exist!"));
        }

        repository.delete(persistedUser);

        return new Result.Success<>(null);
    }
}

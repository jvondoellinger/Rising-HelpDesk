package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.userprofile.DeleteUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class DeleteUserProfileService implements DeleteUserProfileHandler {
	private final UserProfileRepository repository;

	@Override
	public ResultTransformerStep<Void> handle(DeleteUserProfileCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var id = cmd.id();

				   if (id == null)
					   return Result.error(new DomainError("ID_IS_BLANK", "ID is blank."));

				   var optional = repository.findById(id);

				   if (optional.isEmpty())
					   return Result.error(new DomainError("USER_DOES_NOT_EXIST", "User does not exist!"));

				   var userprofile = optional.get();

				   repository.delete(userprofile);

				   return Result.success();
			   });
	}

	@Override
	public Class<DeleteUserProfileCommand> getCommandType() {
		return DeleteUserProfileCommand.class;
	}
}

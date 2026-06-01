package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.userprofile.DeleteUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class DeleteUserProfileService implements DeleteUserProfileHandler {
	private final UserProfileRepository repository;

	@Override
	public ResultB<Void> handle(DeleteUserProfileCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var id = cmd.id();

				   if (id == null)
					   return ResultB.error(new DomainError("ID_IS_BLANK", "ID is blank."));

				   var optional = repository.findById(id);

				   if (optional.isEmpty())
					   return (ResultB<Void>)(ResultB<?>)ResultB.create().map(v -> new DomainError("USER_DOES_NOT_EXIST", "User does not exist!"));

				   var userprofile = optional.get();

				   repository.delete(userprofile);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<DeleteUserProfileCommand> getCommandType() {
		return DeleteUserProfileCommand.class;
	}
}

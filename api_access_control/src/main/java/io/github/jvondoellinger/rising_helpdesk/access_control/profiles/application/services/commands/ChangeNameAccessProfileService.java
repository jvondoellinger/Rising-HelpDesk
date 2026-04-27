package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.ChangeNameAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class ChangeNameAccessProfileService implements ChangeNameAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public ResultTransformerStep<Void> handle(ChangeNameAccessProfileCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.id());

				   if (optional.isEmpty()) {
					   return Result.error(new DomainError("NO_ACCESS_FOUND_ON_PERSISTENCE", "No access found on persistence."));
				   }

				   if (repository.existsByName(cmd.name())) {
					   return Result.error(new DomainError("ALREADY_EXISTS_A_PROFILE_WITH_THIS_NAME", "Already exists a profile with this name!"));
				   }

				   var accessprofile = optional.get();
				   var updated = new AccessProfile(
						 accessprofile.getId(),
						 cmd.name(),
						 accessprofile.getPermissions(),
						 accessprofile.getCreatedAt(),
						 LocalDateTime.now()
				   );

				   repository.save(updated);

				   return Result.success(null);
			   });
	}

	@Override
	public Class<ChangeNameAccessProfileCommand> getCommandType() {
		return ChangeNameAccessProfileCommand.class;
	}
}
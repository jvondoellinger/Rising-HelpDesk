package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.ChangeNameAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeNameAccessProfileService implements ChangeNameAccessProfileHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<Void> handle(ChangeNameAccessProfileCommand cmd) {
		var persistedProfile = repository.queryById(cmd.id());

		if (persistedProfile == null) {
			return new Result.Failure<>(new KernelException("No access found on persistence."));
		}
		if (repository.existsByName(cmd.name())) {
			return new Result.Failure<>(new KernelException("Already exists a profile with this name!"));
		}


		var newValue = new AccessProfile(
			   persistedProfile.getId(),
			   cmd.name() ,
			   persistedProfile.getPermissions(),
			   persistedProfile.getCreatedAt(),
			   LocalDateTime.now()
		);
		repository.save(newValue); // Atualiza no banco de dados

		return new Result.Success<>(null);
	}
}
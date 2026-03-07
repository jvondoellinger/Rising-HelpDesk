package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.ChangePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.ChangePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangePermissionsAccessProfileService implements ChangePermissionsAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionMapper mapper;

	@Override
	public Result<Void> handle(ChangePermissionsAccessProfileCommand cmd) {
		var persistedProfile = repository.queryById(cmd.id());

		if (persistedProfile == null) {
			return new Result.Failure<>(new KernelException("No access found on persistence."));
		}

		var permissions = mapper.from(cmd.permissions());

		if (persistedProfile.getPermissions().equals(permissions)) {
			return new Result.Failure<>(new KernelException("Permissions already granted."));
		}

		var newValue = new AccessProfile(
			   persistedProfile.getId(),
			   persistedProfile.getName() ,
			   permissions,
			   persistedProfile.getCreatedAt(),
			   LocalDateTime.now()
		);
		repository.save(newValue); // Atualiza no banco de dados

		return new Result.Success<>(null);
	}
}

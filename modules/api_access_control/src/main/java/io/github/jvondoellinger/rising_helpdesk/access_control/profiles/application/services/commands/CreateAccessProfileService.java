package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.accessprofile.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionRepository permissionRepository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultB<Void> handle(CreateAccessProfileCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var alreadyExists = repository.existsByName(cmd.name());

				   if (alreadyExists)
					   return ResultB.error(new DomainError("ACCESS_PROFILE_ALREADY_REGISTERED", "Access Profile already registered."));

				   if (permissionRepository.batchExistsById(cmd.permissions()))
					   return ResultB.error(new DomainError("ONE_OR_MORE_PERMISSIONS_WERE_NOT_REGISTERED", "One or more permissions were not registered."));

				   var accessprofile = mapper.from(cmd);
				   repository.save(accessprofile);

				   return ResultB.create();
			   });
	}


	@Override
	public Class<CreateAccessProfileCommand> getCommandType() {
		return CreateAccessProfileCommand.class;
	}
}
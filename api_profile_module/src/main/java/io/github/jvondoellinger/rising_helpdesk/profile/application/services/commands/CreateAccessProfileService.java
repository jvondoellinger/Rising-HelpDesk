package io.github.jvondoellinger.rising_helpdesk.profile.application.services.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CreateAccessProfileService implements CreateAccessProfileHandler {
	private final AccessProfileRepository repository;
	private final PermissionRepository permissionRepository;

	private final AccessProfileMapper mapper;

	@Override
	public Result<Void> handle(CreateAccessProfileCommand cmd) {
		var alreadyExists = repository.existsByName(cmd.name());

		if (alreadyExists) {
			return new Result.Failure<>(KernelException.conflict("Access Profile already registered."));
		}

		var match = cmd
			   .permissions()
			   .stream()
			   .allMatch(permissionRepository::existsById);

		// REMOVE BEFORE
		if (match) {
			return new Result.Failure<>(KernelException.conflict("Any permission has registered."));
		}

		var item = permissionRepository.queryById(cmd.permissions().get(0));
		var test= new ArrayList<Permission>();
		test.add(item);
		var accessprofile = new AccessProfile(cmd.name(), test);
		// REMOVE BEFORE

		var result = repository.save(accessprofile);
		var mapped= mapper.details(result);

		return new Result.Success<Void>(null);
	}

	@Override
	public Class<CreateAccessProfileCommand> getCommandType() {
		return CreateAccessProfileCommand.class;
	}
}

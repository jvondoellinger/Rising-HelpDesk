package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers.AccessProfileResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final CreateAccessProfileHandler accessProfileUseCase;
	private final AccessProfileResponseMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> create(@RequestBody CreateAccessProfileRequest request) {
		var permissions = request.permissions().stream().map(Permission::of).toList();
		var cmd = new CreateAccessProfileCommand(request.name(), new Permissions(permissions));

		return accessProfileUseCase.handle(cmd)
			   .fold(success -> ResponseEntity.accepted().build(),
					 failure -> ResponseEntity.badRequest().body(failure.error().getMessage()));
	}

}

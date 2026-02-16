package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.dtos.CreateAccessProfileDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandBus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final CommandBus bus;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody CreateAccessProfileDTO request) {
		var permissions = request.permissions().stream().map(Permission::of).toList();
		var cmd = new CreateAccessProfileCommand(request.name(), new Permissions(permissions));

		bus.dispatch(cmd);

		return ResponseEntity.noContent().build();
	}
}

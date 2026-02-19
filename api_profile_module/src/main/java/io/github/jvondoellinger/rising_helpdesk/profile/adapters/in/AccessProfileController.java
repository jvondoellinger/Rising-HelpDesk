package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.dtos.AccessProfileResult;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.dtos.CreateAccessProfileDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.useCases.CreateAccessProfileUseCase;
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
	private final CreateAccessProfileUseCase accessProfileUseCase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody CreateAccessProfileRequest request) {
		var permissions = request.permissions().stream().map(Permission::of).toList();
		var cmd = new CreateAccessProfileCommand(request.name(), new Permissions(permissions));

		return accessProfileUseCase.execute(cmd)
			   .fold(success -> {
				   var re = new AccessProfileResult(success.value().accessProfileId().toString(), success.value().createdAt(), success.value().updatedAt());
				   return ResponseEntity.ok(re);
			   }, failure -> ResponseEntity.badRequest().body(failure.error().getMessage()));
	}
}

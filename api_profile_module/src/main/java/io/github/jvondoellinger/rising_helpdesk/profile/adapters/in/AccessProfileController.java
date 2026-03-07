package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers.AccessProfileResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.FindAccessProfileByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final CreateAccessProfileHandler accessProfileUseCase;
	private final FindAccessProfileByIdQueryHandler findAccessProfileByIdQueryHandler;
	private final AccessProfileResponseMapper responseMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> create(@RequestBody CreateAccessProfileRequest request) {
		var permissions = request.permissions().stream().map(Permission::of).toList();
		var cmd = new CreateAccessProfileCommand(request.name(), new Permissions(permissions));

		return accessProfileUseCase.handle(cmd)
			   .fold(
					 success -> ResponseEntity.accepted().build(),
					 failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
			   );
	}

	@GetMapping
	public ResponseEntity<?> get(@RequestParam UUID id) {
		var value = AccessProfileId.of(id.toString());
		var query = new FindAccessProfileByIdQuery(value);
		var result = findAccessProfileByIdQueryHandler.handle(query );

		return result.fold(
			   onSuccess -> ResponseEntity.ok(responseMapper.from(onSuccess.value())),
			   onFailure -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(onFailure.error().getMessage())
		);
	}
}

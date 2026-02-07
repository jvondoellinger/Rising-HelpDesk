package io.github.jvondoellinger.rising_helpdesk.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.application.useCases.CreateAccessProfileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final CreateAccessProfileUseCase useCase;

	@PostMapping
	public AccessProfileDetails create(@RequestBody CreateAccessProfileCommand command) {
		return useCase.execute(command);
	}
}

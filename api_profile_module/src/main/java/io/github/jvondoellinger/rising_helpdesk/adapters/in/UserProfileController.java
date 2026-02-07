package io.github.jvondoellinger.rising_helpdesk.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.application.useCases.CreateUserProfileUseCase;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@AllArgsConstructor
public class UserProfileController {
	private final CreateUserProfileUseCase useCase;

	@PostMapping
	public UserProfileDetails create(@RequestBody CreateUserProfileCommand requestDTO) {
		return useCase.execute(requestDTO);
	}
}

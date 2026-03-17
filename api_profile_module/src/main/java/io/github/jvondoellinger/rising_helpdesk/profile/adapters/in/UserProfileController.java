package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.userprofile.CreateUserProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.UserProfileDetails;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@AllArgsConstructor
public class UserProfileController {
	private final CreateUserProfileHandler useCase;

	@PostMapping
	public UserProfileDetails create(@RequestBody CreateUserProfileCommand requestDTO) {
		return null;
	}
}

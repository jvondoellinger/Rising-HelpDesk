package com.github.jvondoellinger.agp_protocol.api_profile.adapters.in;

import com.github.jvondoellinger.agp_protocol.api_profile.application.commands.CreateUserProfileCommand;
import com.github.jvondoellinger.agp_protocol.api_profile.application.queries.UserProfileDetails;
import com.github.jvondoellinger.agp_protocol.api_profile.application.useCases.CreateUserProfileCommandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@AllArgsConstructor
public class UserProfileController {
	private final CreateUserProfileCommandUseCase useCase;

	@PostMapping
	public UserProfileDetails create(@RequestBody CreateUserProfileCommand requestDTO) {
		return useCase.execute(requestDTO);
	}
}

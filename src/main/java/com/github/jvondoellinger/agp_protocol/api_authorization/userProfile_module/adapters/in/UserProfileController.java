package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.adapters.in;

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
	public CreateUserProfileResponseDTO create(@RequestBody CreateUserProfileRequestDTO requestDTO) {
		return useCase.execute(requestDTO);
	}
}

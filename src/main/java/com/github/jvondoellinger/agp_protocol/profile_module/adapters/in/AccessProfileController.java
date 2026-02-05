package com.github.jvondoellinger.agp_protocol.profile_module.adapters.in;

import com.github.jvondoellinger.agp_protocol.profile_module.application.commands.CreateAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.profile_module.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.profile_module.application.useCases.CreateAccessProfileCommandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final CreateAccessProfileCommandUseCase useCase;

	@PostMapping
	public AccessProfileDetails create(@RequestBody CreateAccessProfileCommand command) {
		return useCase.execute(command);
	}
}

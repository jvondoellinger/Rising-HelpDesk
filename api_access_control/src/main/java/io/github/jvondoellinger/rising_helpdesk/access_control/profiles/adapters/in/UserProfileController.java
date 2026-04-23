package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.HttpResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.userprofile.UserProfileCommandMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.userprofile.UserProfileResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreateUserProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.CommandBus;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@AllArgsConstructor
public class UserProfileController {
	private final CommandBus commandBus;
	private final UserProfileCommandMapper commandMapper;
	private final UserProfileResponseMapper responseMapper;
	private final HttpResponseMapper httpResponseMapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateUserProfileRequest requestDTO) {
		var cmd = commandMapper.toCommand(requestDTO);
		var result = commandBus.send(cmd);

		return httpResponseMapper.fromResult(result);
	}
}

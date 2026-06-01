package io.github.jvondoellinger.api_user_module.features.create_user;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class CreateUserEndpoint {
	private final CreateUserHandler handler;

	@PostMapping
	public ResultB<?> createUser(@RequestBody CreateUserDto createUserDto) {
		var cmd = new CreateUser(createUserDto.nickname(), createUserDto.email(), createUserDto.password());

		var result = handler.handle(cmd);
		return result;
	}

}

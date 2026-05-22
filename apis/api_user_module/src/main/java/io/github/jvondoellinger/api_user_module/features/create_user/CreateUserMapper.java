package io.github.jvondoellinger.api_user_module.features.create_user;

import io.github.jvondoellinger.api_user_module.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserMapper {
	public User toUser(CreateUser cmd) {
		return new User(
			   cmd.nickname(),
			   cmd.email(),
			   cmd.password()
		);
	}
}

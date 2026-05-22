package io.github.jvondoellinger.api_user_module.features.create_user;

import io.github.jvondoellinger.api_user_module.domain.User;
import io.github.jvondoellinger.api_user_module.domain.UserRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUser> {
	private final UserRepository repository;
	private final CreateUserMapper mapper;

	@Override
	public ResultTransformerStep<Void> handle(CreateUser cmd) {
		return null;
	}

	@Override
	public Class<CreateUser> getCommandType() {
		return null;
	}
}

package io.github.jvondoellinger.api_user_module.features.create_user;

import io.github.jvondoellinger.api_user_module.domain.UserRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUser> {
	private final UserRepository repository;
	private final CreateUserMapper mapper;

	@Override
	public ResultB<Void> handle(CreateUser cmd) {
		return ResultB.create()
			   .map(x -> {
				   var entity = mapper.toUser(cmd);
				   repository.save(entity);

				   return null;
			   });
	}

	@Override
	public Class<CreateUser> getCommandType() {
		return null;
	}
}

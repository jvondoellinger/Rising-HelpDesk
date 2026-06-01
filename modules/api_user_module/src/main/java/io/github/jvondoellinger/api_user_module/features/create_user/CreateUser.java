package io.github.jvondoellinger.api_user_module.features.create_user;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;

public record CreateUser(String nickname,
					String email,
					String password) implements Command {
}

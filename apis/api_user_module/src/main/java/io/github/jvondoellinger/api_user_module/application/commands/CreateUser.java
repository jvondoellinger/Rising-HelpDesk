package io.github.jvondoellinger.api_user_module.application.commands;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;

public record CreateUser(String nickname, String email, String password) implements Command {
}

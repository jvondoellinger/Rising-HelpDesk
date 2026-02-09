package io.github.jvondoellinger.rising_helpdesk.application.services;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandBus {
	private final List<CommandUseCase<?,?>> handlers;

	public CommandBus(List<CommandUseCase<?, ?>> handlers) {
		this.handlers = handlers;
	}

	<I extends Command> void handle(I cmd) {
		var useCase = handlers.stream()
			   .filter(x -> x.getClass().isAssignableFrom(cmd.getClass()))
			   .findFirst()
			   .map(u -> (CommandUseCase<I, ?>) u)
			   .orElseThrow();
		useCase.execute(cmd);
	}
}

package io.github.jvondoellinger.rising_helpdesk.application.services;

import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandBus {
	private final List<CommandUseCase<?,?>> useCases;

	public CommandBus(List<CommandUseCase<?, ?>> useCases) {
		this.useCases = useCases;
	}

	<I extends Command, O> O handle(I cmd) {
		var useCase = useCases.stream()
			   .filter(x -> x.getClass().isAssignableFrom(cmd.getClass()))
			   .findFirst()
			   .orElseThrow();
		return ((CommandUseCase<I,O>) useCase).execute(cmd);
	}
}

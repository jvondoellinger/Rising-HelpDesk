package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.ResultA;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
public class CommandBusImpl implements CommandBus {
	private final HashMap<Class<Command>, CommandHandler<Command>> hashMap;

	public CommandBusImpl(List<CommandHandler<? extends Command>> handlers) {
		hashMap = new HashMap<>();

		System.out.println("\nInicializando a injenção de handlers no CommandBus...");
		handlers.forEach(x -> {
			var handler = (CommandHandler<Command>) x;
			hashMap.put(handler.getCommandType(), handler);

			System.out.println("- Adicionando handler: " + x.getCommandType().getSimpleName());
		});
		System.out.println("CommandBus pronto para uso!\n");
	}


	public ResultB<Void> send(Command cmd) {
		var handler = hashMap.get(cmd.getClass());

		if (handler == null) {
			var error = new DomainError("NO_HANDLER_FOUND", "No handler found.");
			return ResultB.error(error);
		}

		return handler.handle(cmd);

	}



	@Deprecated
	private ResultA<Void> toResultA(ResultB<Void> result) {
		if (!result.hasErrors()) {
			final DomainError[] error = new DomainError[1];
			result.mapIfError(e -> error[0] = e);
			return ResultA.error(error[0]);
		}
		return ResultA.success();
	}
}

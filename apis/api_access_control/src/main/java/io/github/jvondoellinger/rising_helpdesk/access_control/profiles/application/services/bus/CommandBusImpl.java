package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultA;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service("profileCommandBus")
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
			return ResultB.error(new DomainError("NO_HANDLER_FOUND", "No handler found."));
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

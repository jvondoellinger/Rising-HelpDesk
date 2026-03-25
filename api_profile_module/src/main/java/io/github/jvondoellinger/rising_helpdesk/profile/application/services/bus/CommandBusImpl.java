package io.github.jvondoellinger.rising_helpdesk.profile.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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


	public Result<Void> send(Command cmd) {
		var handler = hashMap.get(cmd.getClass());

		if (handler == null) {
			return new Result.Failure<>("No handler found.");
		}

		return handler.handle(cmd);
	}
}

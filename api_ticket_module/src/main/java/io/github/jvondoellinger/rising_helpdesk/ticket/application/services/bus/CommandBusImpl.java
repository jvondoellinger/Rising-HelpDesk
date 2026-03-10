package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommandBusImpl {
    private final HashMap<Class<Command>, CommandHandler<Command>> hashMap;

    public CommandBusImpl(List<CommandHandler<? extends Command>> handlers) {
        hashMap = new HashMap<>();

        System.out.println("Inicializando a injenção de handlers no CommandBus...");
        handlers.forEach(x -> {
            var handler = (CommandHandler<Command>) x;
            hashMap.put(handler.getCommandType(), handler);

            System.out.println("- Adicionando handler: " + x.getCommandType().getName());
        });
        System.out.println("CommandBus pronto para uso!");
    }


    public Result<Void> send(Command cmd) {
        var handler = hashMap.get(cmd.getClass());

        if (handler != null) {
            return new Result.Failure<>(new KernelException("No handler found."));
        }

        return handler.handle(cmd);
    }
}

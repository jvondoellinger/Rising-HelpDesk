package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QueryBusImpl implements QueryBus {
    private final HashMap<Class<?>, QueryHandler<? extends Query<?>, ?>> hashMap;

    public QueryBusImpl(List<QueryHandler<? extends Query<?>, ?>> handlers) {
        this.hashMap = new HashMap<>();

        System.out.println("\nInicializando a injenção de handlers no QueryBus");
        for (var handler : handlers) {
            System.out.println("- Adicionando handler: "+ handler.getClass().getSimpleName());
            hashMap.put(handler.getQueryType(), handler);
        }
        System.out.println("QueryBus pronto para uso!\n");
    }

    @Override
    public <R> Result<R, String> send(Query<R> cmd) {
        var handler = hashMap.get(cmd.getClass());

        if (handler == null) {
            return Result.failure("No handler found");
        }

        return  ((QueryHandler<Query<R>, R>)handler).handle(cmd);
    }
}

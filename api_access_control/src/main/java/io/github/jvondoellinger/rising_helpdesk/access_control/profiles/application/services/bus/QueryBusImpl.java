package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service("profileQueryBus")
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
    public <R> Result<R> send(Query<R> cmd) {
        var handler = hashMap.get(cmd.getClass());

        if (handler == null) {
            return Result.error(new DomainError("NO_HANDLER_FOUND", "No handler found"));
        }

        return  ((QueryHandler<Query<R>, R>)handler)
                .handle(cmd)
                .then();
    }
}

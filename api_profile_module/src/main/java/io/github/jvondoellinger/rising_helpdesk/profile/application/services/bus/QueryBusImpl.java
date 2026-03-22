package io.github.jvondoellinger.rising_helpdesk.profile.application.services.bus;

import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
            return new Result.Failure<>(new KernelException("No handler found"));
        }

        return  ((QueryHandler<Query<R>, R>)handler).handle(cmd);
    }
}

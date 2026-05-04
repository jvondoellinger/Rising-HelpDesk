package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;

public interface QueryBus {
    <R> Result<R> send(Query<R> cmd);
}

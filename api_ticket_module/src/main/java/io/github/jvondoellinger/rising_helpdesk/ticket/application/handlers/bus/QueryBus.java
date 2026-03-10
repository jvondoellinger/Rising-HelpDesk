package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

public interface QueryBus {
    <R> Result<R> send(Query<R> cmd);
}

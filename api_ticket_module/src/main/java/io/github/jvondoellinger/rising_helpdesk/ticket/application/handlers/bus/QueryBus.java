package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;

public interface QueryBus {
    <R> ResultV1<R, String> send(Query<R> cmd);
}

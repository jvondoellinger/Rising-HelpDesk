package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultA;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;

public interface QueryBus {
    <R> ResultB<R> send(Query<R> cmd);
}

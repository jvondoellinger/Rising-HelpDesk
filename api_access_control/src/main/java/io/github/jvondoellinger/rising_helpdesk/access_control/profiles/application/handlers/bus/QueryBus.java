package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

public interface QueryBus {
    <R> Result<R> send(Query<R> cmd);
}

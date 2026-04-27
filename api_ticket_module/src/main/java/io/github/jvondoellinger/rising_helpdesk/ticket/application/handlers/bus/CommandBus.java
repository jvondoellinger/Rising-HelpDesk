package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;

public interface CommandBus {
    Result<Void> send(Command cmd);
}

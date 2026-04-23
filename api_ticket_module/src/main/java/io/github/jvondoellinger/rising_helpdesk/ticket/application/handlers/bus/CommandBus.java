package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

public interface CommandBus {
    Result<Void, String> send(Command cmd);
}

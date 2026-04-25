package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

public interface CommandBus {
    Result<Void> send(Command cmd);
}
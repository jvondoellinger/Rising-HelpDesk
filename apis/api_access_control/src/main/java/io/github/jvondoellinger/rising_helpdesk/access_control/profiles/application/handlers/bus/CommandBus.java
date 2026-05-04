package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;

public interface CommandBus {
    Result<Void> send(Command cmd);
}
package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

public interface CommandBus {
    Result<Void> send(Command cmd);
}
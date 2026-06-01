package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

public interface CommandBus {
    ResultB<Void> send(Command cmd);
}
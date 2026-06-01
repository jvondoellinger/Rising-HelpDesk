package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security;

import java.util.UUID;

public interface CurrentUserService {
	UUID getUserId();
}

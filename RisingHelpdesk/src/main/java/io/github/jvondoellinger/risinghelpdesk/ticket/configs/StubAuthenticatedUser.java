package io.github.jvondoellinger.risinghelpdesk.ticket.configs;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public class StubAuthenticatedUser implements AuthenticatedUser {
	@Override
	public UUID getCurrentUserId() {
		return UUID.randomUUID();
	}
}

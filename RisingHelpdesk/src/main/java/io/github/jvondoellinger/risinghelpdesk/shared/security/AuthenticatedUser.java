package io.github.jvondoellinger.risinghelpdesk.shared.security;

import java.util.UUID;

public interface AuthenticatedUser {
	UUID getCurrentUserId();

}

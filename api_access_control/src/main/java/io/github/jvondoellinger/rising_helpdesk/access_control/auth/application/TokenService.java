package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import java.util.List;
import java.util.UUID;

public interface TokenService {
	// gen and validate
	Result<EncodedToken> generate(TokenPayload content);
	Result<TokenPayload> verify(EncodedToken token);

	// Revoke
	Result<Void> revoke(EncodedToken encodedToken);
	Result<Void> revokeAll(UUID userId);
	Result<Boolean> isRevoked(EncodedToken encodedToken);
	Result<Boolean> isRevoked(UUID jti, UUID userId);

	// Counters
	Result<Long> countJtiByUser(UUID userId);
}

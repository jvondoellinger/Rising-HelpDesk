package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;

import java.util.UUID;

public interface TokenService {
	// gen and validate
	Result<EncodedToken> generate(TokenPayload content);

	/**
	 *
	 * @return Short-term guest token
	 */
	Result<EncodedToken> generate();

	// Result<EncodedToken> generate();
	Result<TokenPayload> verify(EncodedToken token);

	// Revoke
	Result<Void> revoke(EncodedToken encodedToken);
	Result<Void> revokeAll(UUID userId);
	Result<Boolean> isRevoked(EncodedToken encodedToken);
	Result<Boolean> isRevoked(UUID jti, UUID userId);

	// Counters
	Result<Long> countJtiByUser(UUID userId);
}

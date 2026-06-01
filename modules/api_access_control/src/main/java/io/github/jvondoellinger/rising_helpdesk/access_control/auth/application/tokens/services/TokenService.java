package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

import java.util.UUID;

public interface TokenService {
	// gen and validate
	ResultB<EncodedToken> generate(TokenPayload content);

	/**
	 *
	 * @return Short-term guest token
	 */
	ResultB<EncodedToken> generate();

	// ResultA<EncodedToken> generate();
	ResultB<TokenPayload> verify(EncodedToken token);

	// Revoke
	ResultB<Void> revoke(EncodedToken encodedToken);
	ResultB<Void> revokeAll(UUID userId);
	ResultB<Boolean> isRevoked(EncodedToken encodedToken);
	ResultB<Boolean> isRevoked(UUID jti, UUID userId);

	// Counters
	ResultB<Long> countJtiByUser(UUID userId);
}

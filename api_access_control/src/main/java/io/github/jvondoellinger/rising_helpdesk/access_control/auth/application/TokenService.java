package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;

import java.util.UUID;

public interface TokenService {
	// gen and validate
	ResultV1<EncodedToken, String> generate(TokenPayload content);
	ResultV1<TokenPayload, String> verify(EncodedToken token);

	// Revoke
	ResultV1<Void, String> revoke(EncodedToken encodedToken);
	ResultV1<Void, String> revokeAll(UUID userId);
	ResultV1<Boolean, String> isRevoked(EncodedToken encodedToken);
	ResultV1<Boolean, String> isRevoked(UUID jti, UUID userId);

	// Counters
	ResultV1<Long, String> countJtiByUser(UUID userId);
}

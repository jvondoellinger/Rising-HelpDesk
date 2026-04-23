package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import java.util.List;
import java.util.UUID;

public interface TokenService {
	// gen and validate
	Result<EncodedToken, String> generate(TokenPayload content);
	Result<TokenPayload, String> verify(EncodedToken token);

	// Revoke
	Result<Void, String> revoke(EncodedToken encodedToken);
	Result<Void, String> revokeAll(UUID userId);
	Result<Boolean, String> isRevoked(EncodedToken encodedToken);
	Result<Boolean, String> isRevoked(UUID jti, UUID userId);

	// Counters
	Result<Long, String> countJtiByUser(UUID userId);
}

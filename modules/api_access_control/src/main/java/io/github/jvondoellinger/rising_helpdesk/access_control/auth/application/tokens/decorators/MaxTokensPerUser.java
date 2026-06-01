package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.decorators;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.settings.TokenSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl.JwtTokenServiceImpl;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class MaxTokensPerUser implements TokenServiceDecorator {
	private final JwtTokenServiceImpl service;
	private final TokenSettings settings;

	@Override
	public ResultB<EncodedToken> generate(TokenPayload payload) {
		var countResult = countJtiByUser(payload.getSubject());

		if (countResult.hasErrors()) {
			return countResult.<EncodedToken>map(x -> null);
		}

		if (countResult.getOrDefault(Long.MAX_VALUE) > settings.getMaxTokensPerUser()) {
			return ResultB.error(new DomainError("MAXIMUM_NUMBER_OF_TOKENS_REACHED_PER_USER", "Maximum number of tokens reached per user!"));
		}

		return service.generate(payload);
	}

	@Override
	public ResultB<EncodedToken> generate() {
		return service.generate();
	}

	@Override
	public ResultB<TokenPayload> verify(EncodedToken token) {
		return service.verify(token);
	}

	@Override
	public ResultB<Void> revoke(EncodedToken encodedToken) {
		return service.revoke(encodedToken);
	}

	@Override
	public ResultB<Void> revokeAll(UUID userId) {
		return service.revokeAll(userId);
	}

	@Override
	public ResultB<Boolean> isRevoked(EncodedToken encodedToken) {
		return service.isRevoked(encodedToken);
	}

	@Override
	public ResultB<Boolean> isRevoked(UUID jti, UUID userId) {
		return service.isRevoked(jti,userId);
	}

	@Override
	public ResultB<Long> countJtiByUser(UUID userId) {
		return service.countJtiByUser(userId);
	}
}

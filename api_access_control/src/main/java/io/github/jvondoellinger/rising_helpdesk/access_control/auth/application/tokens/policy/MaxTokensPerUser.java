package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.policy;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.config.TokenSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl.JwtTokenServiceImpl;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MaxTokensPerUser implements TokenService {
	private final JwtTokenServiceImpl service;
	private final TokenSettings settings;

	@Override
	public Result<EncodedToken> generate(TokenPayload payload) {
		var countResult = countJtiByUser(payload.getSubject());

		if (countResult.isError()) {
			return countResult.castWhenError();
		}

		if (countResult.getValue() > settings.getMaxTokensPerUser()) {
			return Result.error(new DomainError("MAXIMUM_NUMBER_OF_TOKENS_REACHED_PER_USER", "Maximum number of tokens reached per user!"));
		}

		return service.generate(payload);
	}

	@Override
	public Result<EncodedToken> generate() {
		return service.generate();
	}

	@Override
	public Result<TokenPayload> verify(EncodedToken token) {
		return service.verify(token);
	}

	@Override
	public Result<Void> revoke(EncodedToken encodedToken) {
		return service.revoke(encodedToken);
	}

	@Override
	public Result<Void> revokeAll(UUID userId) {
		return service.revokeAll(userId);
	}

	@Override
	public Result<Boolean> isRevoked(EncodedToken encodedToken) {
		return service.isRevoked(encodedToken);
	}

	@Override
	public Result<Boolean> isRevoked(UUID jti, UUID userId) {
		return service.isRevoked(jti,userId);
	}

	@Override
	public Result<Long> countJtiByUser(UUID userId) {
		return service.countJtiByUser(userId);
	}
}

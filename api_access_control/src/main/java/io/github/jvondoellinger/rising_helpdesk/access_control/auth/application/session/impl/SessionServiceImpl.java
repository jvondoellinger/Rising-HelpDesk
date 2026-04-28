package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.services.SessionService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.config.TokenSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {
	private final StringRedisTemplate template;
	private final JtiKeyFactory keyFactory;
	// private final ClaimsFactory claimsFactory;
	// private final JwtFactory jwtFactory;
	private final TokenSettings settings;
	private final TokenPayloadFactory payloadFactory;

	@Override
	@FixAfter
	public Result<SessionData> create(TokenPayload payload) {
		return null;
	}

	@Override
	public Result<Boolean> canIssueNewToken(UUID userId) {
		var key = keyFactory.getJtiKey(userId);
		var activeTokens = template.opsForSet().size(key);

		if (Objects.isNull(activeTokens)) {
			return Result.error(new DomainError("UNEXPECTED_ERROR_COUNT_VALUE_RETURNED_IS_NULL", "Unexpected error! Count value returned is null."));
		}

		if (activeTokens >= settings.getMaxTokensPerUser()) {
			return Result.success(false);
		}

		return Result.success(true);
	}

	@Override
	public Result<Integer> getActiveTokensCount(UUID userId) {
		return null;
	}

	@Override
	public Result<List<SessionData>> getActiveSessions(UUID userId) {
		return null;
	}
}

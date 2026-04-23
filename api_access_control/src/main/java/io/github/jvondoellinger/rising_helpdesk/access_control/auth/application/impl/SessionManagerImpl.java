package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.SessionManager;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.config.AuthenticationSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.ClaimsFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JwtFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SessionManagerImpl implements SessionManager {
	private final StringRedisTemplate template;
	private final JtiKeyFactory keyFactory;
	// private final ClaimsFactory claimsFactory;
	// private final JwtFactory jwtFactory;
	private final AuthenticationSettings settings;
	private final TokenPayloadFactory payloadFactory;

	@Override
	public Result<Boolean, String> canIssueNewToken(UUID userId) {
		var key = keyFactory.getJtiKey(userId);
		var activeTokens = template.opsForSet().size(key);

		if (Objects.isNull(activeTokens)) {
			return Result.failure("Unexpected error! Count value returned is null.");
		}

		if (activeTokens >= settings.getMaxTokensPerUser()) {
			return Result.success(false);
		}

		return Result.success(true);
	}

	@Override
	public Result<Integer, String> getActiveTokensCount(UUID userId) {
		return null;
	}

	@Override
	public Result<List<SessionData>, String> getActiveSessions(UUID userId) {
		return null;
	}
}

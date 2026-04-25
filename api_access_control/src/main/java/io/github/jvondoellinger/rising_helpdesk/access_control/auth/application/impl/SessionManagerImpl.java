package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.SessionManager;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.config.AuthenticationSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
	public ResultV1<Boolean, String> canIssueNewToken(UUID userId) {
		var key = keyFactory.getJtiKey(userId);
		var activeTokens = template.opsForSet().size(key);

		if (Objects.isNull(activeTokens)) {
			return ResultV1.failure("Unexpected error! Count value returned is null.");
		}

		if (activeTokens >= settings.getMaxTokensPerUser()) {
			return ResultV1.success(false);
		}

		return ResultV1.success(true);
	}

	@Override
	public ResultV1<Integer, String> getActiveTokensCount(UUID userId) {
		return null;
	}

	@Override
	public ResultV1<List<SessionData>, String> getActiveSessions(UUID userId) {
		return null;
	}
}

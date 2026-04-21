package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JtiKeyFactory {
	private static final String DEFAULT_ACTIVE_JTIS = "auth:user:%s:active_jtis";
	private static final String DEFAULT_REVOKED_JTIS = "auth:revoked:jti:%s";

	public String getJtiRevokedKey(UUID userId) {
		return DEFAULT_REVOKED_JTIS.formatted(userId);
	}
	public String getJtiKey(UUID userId) {
		return DEFAULT_ACTIVE_JTIS.formatted(userId);
	}
}

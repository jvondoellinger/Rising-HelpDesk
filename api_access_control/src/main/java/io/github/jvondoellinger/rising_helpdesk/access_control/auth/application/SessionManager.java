package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import java.util.List;
import java.util.UUID;

public interface SessionManager {
	Result<SessionData, String> create(TokenPayload payload);

	// Session control
	Result<Boolean, String> canIssueNewToken(UUID userId);

	Result<Integer, String> getActiveTokensCount(UUID userId);
	Result<List<SessionData>, String> getActiveSessions(UUID userId);
}

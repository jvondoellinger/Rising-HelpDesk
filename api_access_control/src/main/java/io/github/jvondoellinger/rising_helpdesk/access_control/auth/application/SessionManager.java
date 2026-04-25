package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

import java.util.List;
import java.util.UUID;

public interface SessionManager {
	Result<SessionData> create(TokenPayload payload);

	// Session control
	Result<Boolean> canIssueNewToken(UUID userId);

	Result<Integer> getActiveTokensCount(UUID userId);
	Result<List<SessionData>> getActiveSessions(UUID userId);
}

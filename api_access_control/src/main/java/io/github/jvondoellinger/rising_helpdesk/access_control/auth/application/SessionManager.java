package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;

import java.util.List;
import java.util.UUID;

public interface SessionManager {
	ResultV1<SessionData, String> create(TokenPayload payload);

	// Session control
	ResultV1<Boolean, String> canIssueNewToken(UUID userId);

	ResultV1<Integer, String> getActiveTokensCount(UUID userId);
	ResultV1<List<SessionData>, String> getActiveSessions(UUID userId);
}

package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.services;


import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.data.SessionData;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;

import java.util.List;
import java.util.UUID;

public interface SessionService {
	ResultB<SessionData> create(TokenPayload payload);

	// Session control
	ResultB<Boolean> canIssueNewToken(UUID userId);

	ResultB<Integer> getActiveTokensCount(UUID userId);
	ResultB<List<SessionData>> getActiveSessions(UUID userId);
}

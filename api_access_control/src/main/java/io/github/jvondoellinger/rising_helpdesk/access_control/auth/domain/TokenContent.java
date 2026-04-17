package io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TokenContent {
	private final UUID subject;
	private final List<UUID> accessProfileIds;
	private final Date expiration;
	private final Date issueAt;

	public TokenContent(UUID subject,
					List<UUID> accessProfileIds,
					Date expiration,
					Date issueAt) {
		this.subject = subject;
		this.accessProfileIds = accessProfileIds;
		this.expiration = expiration;
		this.issueAt = issueAt;
	}

	public UUID getSubject() {
		return subject;
	}
	public List<UUID> getAccessProfileIds() {
		return accessProfileIds;
	}
	public Date getExpiration() {
		return expiration;
	}
	public Date getIssueAt() {
		return issueAt;
	}
}

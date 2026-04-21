package io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TokenPayload {
	private final UUID jti;
	private final UUID subject;
	private final List<UUID> accessProfileIds;
	private final Date expiration;
	private final Date issueAt;

	public TokenPayload(UUID jti,
					UUID subject,
					List<UUID> accessProfileIds,
					Date expiration,
					Date issueAt) {
		this.jti = jti;
		this.subject = subject;
		this.accessProfileIds = accessProfileIds;
		this.expiration = expiration;
		this.issueAt = issueAt;
	}
	private TokenPayload(UUID subject,
					List<UUID> accessProfileIds,
					Date expiration,
					Date issueAt) {
		this.jti = UUID.randomUUID();
		this.subject = subject;
		this.accessProfileIds = accessProfileIds;
		this.expiration = expiration;
		this.issueAt = issueAt;
	}

	public static TokenPayload create(UUID subject,
							    List<UUID> accessProfileIds,
							    Date expiration,
							    Date issueAt,
							    Long version) {
		return new TokenPayload(subject, accessProfileIds, expiration, issueAt);
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
	public UUID getJti() {
		return jti;
	}
}

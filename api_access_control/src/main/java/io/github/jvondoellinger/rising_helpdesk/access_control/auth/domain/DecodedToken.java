package io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DecodedToken extends AbstractToken {
	private final UUID subject;
	private final List<UUID> accessProfileIds;
	private final Date expiration;
	private final Date issueAt;

	public DecodedToken(String token,
					UUID subject,
					List<UUID> accessProfileIds,
					Date expiration, Date issueAt) {
		super(token);
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

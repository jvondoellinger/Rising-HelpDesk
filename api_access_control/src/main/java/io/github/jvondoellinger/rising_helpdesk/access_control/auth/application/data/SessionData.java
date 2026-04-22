package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data;

import java.net.Socket;
import java.util.Date;

public class SessionData {
	private String userAgent;
	private Socket ip;
	private Date expiresAt;
	private boolean revoked;

	public SessionData(String userAgent, Socket ip, Date expiresAt, boolean revoked) {
		this.userAgent = userAgent;
		this.ip = ip;
		this.expiresAt = expiresAt;
		this.revoked = revoked;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Socket getIp() {
		return ip;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public boolean isRevoked() {
		return revoked;
	}
}

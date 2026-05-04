package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.session.data;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class SessionData {
	private final String userAgent;
	private final InetAddress ip;

	public SessionData(String userAgent, InetAddress ip) {
		this.userAgent = userAgent;
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}
	public InetAddress getIp() {
		return ip;
	}
}

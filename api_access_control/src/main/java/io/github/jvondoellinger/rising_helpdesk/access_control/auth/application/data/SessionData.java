package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.data;

import java.net.Socket;
import java.util.Date;

public class SessionData {
	private boolean revoked;
	private Date expiresAt;
	private Socket ip;
	private String userAgent;
}

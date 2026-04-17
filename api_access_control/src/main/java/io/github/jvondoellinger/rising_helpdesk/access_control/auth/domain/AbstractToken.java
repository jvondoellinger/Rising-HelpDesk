package io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain;

public class AbstractToken {
	protected final String token;
	protected AbstractToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return token;
	}
}

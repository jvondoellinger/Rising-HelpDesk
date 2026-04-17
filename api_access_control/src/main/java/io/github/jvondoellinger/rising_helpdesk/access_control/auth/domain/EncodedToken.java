package io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain;

public class EncodedToken extends AbstractToken {
	public EncodedToken(String encoded) {
		super(encoded);
	}
}

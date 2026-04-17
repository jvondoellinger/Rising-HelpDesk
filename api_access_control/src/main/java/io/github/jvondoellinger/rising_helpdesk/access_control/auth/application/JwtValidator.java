package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

@Service
public class JwtValidator {
	public boolean varify(String token) {
		return false;
	}

	private void format(String t) {
	}

}

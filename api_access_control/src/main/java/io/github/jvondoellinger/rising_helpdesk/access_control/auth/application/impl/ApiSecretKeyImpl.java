package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.ApiSecretKey;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class ApiSecretKeyImpl implements ApiSecretKey {
	private static final SecretKey secretKey = Jwts.SIG.HS512.key().build();

	@Override
	public SecretKey getCurrent() {
		return secretKey;
	}
}

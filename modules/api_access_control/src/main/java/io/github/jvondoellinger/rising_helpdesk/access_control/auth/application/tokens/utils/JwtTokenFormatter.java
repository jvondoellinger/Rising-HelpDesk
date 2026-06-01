package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.utils;

public class JwtTokenFormatter {
	public static String formatToken(String token) {
		if (token == null) {
			return null;
		}

		final String prefix = "Bearer ";
		if (token.startsWith(prefix)) {
			return token.replace(prefix, "");
		}

		return token;
	}
}

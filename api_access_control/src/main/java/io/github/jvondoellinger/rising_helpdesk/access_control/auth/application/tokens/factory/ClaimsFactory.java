package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.secretKey.ApiSecretKey;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator.ClaimsExceptionTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.utils.JwtTokenFormatter.formatToken;

@Service
@AllArgsConstructor
public class ClaimsFactory {
	private final ClaimsExceptionTranslator exceptionTranslator;
	private final ApiSecretKey secretKey;

	public Result<Claims> factory(EncodedToken encodedToken) {
		return exceptionTranslator.translate(() ->
			Jwts.parser()
				   .verifyWith(secretKey.getCurrent())
				   .build()
				   .parseSignedClaims(formatToken(encodedToken.toString()))
				   .getPayload()
		);
	}
}

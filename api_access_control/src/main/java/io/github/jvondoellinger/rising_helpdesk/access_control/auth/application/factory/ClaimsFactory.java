package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.ApiSecretKey;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.ClaimsExceptionTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.utils.JwtTokenFormatter.formatToken;

@Service
@AllArgsConstructor
public class ClaimsFactory {
	private final ClaimsExceptionTranslator exceptionTranslator;
	private final ApiSecretKey secretKey;

	public ResultV1<Claims, String> factory(EncodedToken encodedToken) {
		return exceptionTranslator.translate(() ->
			Jwts.parser()
				   .verifyWith(secretKey.getCurrent())
				   .build()
				   .parseSignedClaims(formatToken(encodedToken.toString()))
				   .getPayload()
		);
	}
}

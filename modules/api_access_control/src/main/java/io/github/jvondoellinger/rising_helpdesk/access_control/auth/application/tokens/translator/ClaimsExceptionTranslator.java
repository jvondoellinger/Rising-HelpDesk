package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
public class ClaimsExceptionTranslator implements ExceptionTranslator<Claims> {

	@Override
	public ResultB<Claims> translate(Supplier<Claims> func) {
		try{
			return ResultB.of(func.get());
		} catch (ExpiredJwtException e) {
			return ResultB.error(new DomainError("TOKEN_EXPIRED", "Token expired"));

		} catch (SecurityException e) {
			return ResultB.error(new DomainError("INVALID_SIGNATURE", "Invalid signature"));

		} catch (MalformedJwtException e) {
			return ResultB.error(new DomainError("MALFORMED_TOKEN", "Malformed token"));

		} catch (UnsupportedJwtException e) {
			return ResultB.error(new DomainError("UNSUPPORTED_TOKEN", "Unsupported token"));

		} catch (IllegalArgumentException e) {
			return ResultB.error(new DomainError("TOKEN_IS_NULL_OR_EMPTY", "Token is null or empty"));

		} catch (JwtException e) {
			return ResultB.error(new DomainError("INVALID_TOKEN", "Invalid token"));
		}
	}
}

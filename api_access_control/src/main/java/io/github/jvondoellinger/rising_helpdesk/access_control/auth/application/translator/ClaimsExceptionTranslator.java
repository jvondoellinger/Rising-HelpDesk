package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
public class ClaimsExceptionTranslator implements ExceptionTranslator<Claims> {

	@Override
	public Result<Claims> translate(Supplier<Claims> func) {
		try{
			return Result.success(func.get());
		} catch (ExpiredJwtException e) {
			return Result.error(new DomainError("TOKEN_EXPIRED", "Token expired"));

		} catch (SecurityException e) {
			return Result.error(new DomainError("INVALID_SIGNATURE", "Invalid signature"));

		} catch (MalformedJwtException e) {
			return Result.error(new DomainError("MALFORMED_TOKEN", "Malformed token"));

		} catch (UnsupportedJwtException e) {
			return Result.error(new DomainError("UNSUPPORTED_TOKEN", "Unsupported token"));

		} catch (IllegalArgumentException e) {
			return Result.error(new DomainError("TOKEN_IS_NULL_OR_EMPTY", "Token is null or empty"));

		} catch (JwtException e) {
			return Result.error(new DomainError("INVALID_TOKEN", "Invalid token"));
		}
	}
}

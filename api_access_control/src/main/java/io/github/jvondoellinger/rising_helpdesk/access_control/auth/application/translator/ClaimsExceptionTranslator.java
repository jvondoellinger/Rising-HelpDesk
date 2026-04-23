package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class ClaimsExceptionTranslator implements ExceptionTranslator<Claims> {

	@Override
	public Result<Claims, String> translate(Supplier<Claims> func) {
		try{
			return Result.success(func.get());
		} catch (ExpiredJwtException e) {
			return Result.failure("Token expired");

		} catch (SecurityException e) {
			return Result.failure("Invalid signature");

		} catch (MalformedJwtException e) {
			return Result.failure("Malformed token");

		} catch (UnsupportedJwtException e) {
			return Result.failure("Unsupported token");

		} catch (IllegalArgumentException e) {
			return Result.failure("Token is null or empty");

		} catch (JwtException e) {
			return Result.failure("Invalid token");
		}
	}
}

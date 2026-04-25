package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class ClaimsExceptionTranslator implements ExceptionTranslator<Claims> {

	@Override
	public ResultV1<Claims, String> translate(Supplier<Claims> func) {
		try{
			return ResultV1.success(func.get());
		} catch (ExpiredJwtException e) {
			return ResultV1.failure("Token expired");

		} catch (SecurityException e) {
			return ResultV1.failure("Invalid signature");

		} catch (MalformedJwtException e) {
			return ResultV1.failure("Malformed token");

		} catch (UnsupportedJwtException e) {
			return ResultV1.failure("Unsupported token");

		} catch (IllegalArgumentException e) {
			return ResultV1.failure("Token is null or empty");

		} catch (JwtException e) {
			return ResultV1.failure("Invalid token");
		}
	}
}

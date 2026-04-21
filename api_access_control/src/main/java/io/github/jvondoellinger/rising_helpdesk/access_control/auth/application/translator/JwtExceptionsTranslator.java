package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JwtExceptionsTranslator implements ExceptionTranslator<String>{

	/**
	 *
	 * @apiNote This translates ONLY the possible errors that can occur during the creation of the JWT token from Jwts (jjwt library).
	 */
	public Result<String> translate(Supplier<String> func) {
		try {
			return Result.success(func.get());
		} catch (WeakKeyException e) {
			return Result.failure("Weak key");

		} catch (SerializationException e) {
			return Result.failure("Invalid claims serialization");

		} catch (SecurityException e) {
			return Result.failure("Signing error");
		} catch (IllegalArgumentException e) {
			return Result.failure("Invalid JWT data");
		}
	}
}

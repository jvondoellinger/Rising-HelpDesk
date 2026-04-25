package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

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
			return Result.error(new DomainError("WEAK_KEY", "Weak key"));

		} catch (SerializationException e) {
			return Result.error(new DomainError("INVALID_CLAIMS_SERIALIZATION", "Invalid claims serialization"));

		} catch (SecurityException e) {
			return Result.error(new DomainError("SIGNING_ERROR", "Signing error"));
		} catch (IllegalArgumentException e) {
			return Result.error(new DomainError("INVALID_JWT_DATA", "Invalid JWT data"));
		}
	}
}

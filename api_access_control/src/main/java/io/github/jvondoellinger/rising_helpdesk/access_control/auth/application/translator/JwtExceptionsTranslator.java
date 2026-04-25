package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JwtExceptionsTranslator implements ExceptionTranslator<String>{

	/**
	 *
	 * @apiNote This translates ONLY the possible errors that can occur during the creation of the JWT token from Jwts (jjwt library).
	 */
	public ResultV1<String, String> translate(Supplier<String> func) {
		try {
			return ResultV1.success(func.get());
		} catch (WeakKeyException e) {
			return ResultV1.failure("Weak key");

		} catch (SerializationException e) {
			return ResultV1.failure("Invalid claims serialization");

		} catch (SecurityException e) {
			return ResultV1.failure("Signing error");
		} catch (IllegalArgumentException e) {
			return ResultV1.failure("Invalid JWT data");
		}
	}
}

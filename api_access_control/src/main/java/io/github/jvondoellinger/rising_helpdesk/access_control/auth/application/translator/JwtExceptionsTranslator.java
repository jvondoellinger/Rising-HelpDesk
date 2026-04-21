package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JwtExceptionsTranslator {
	public <T> Result<T> translate(Supplier<Result<T>> func) {
		try {
			return func.get();
		} catch (SignatureException e) {
			return Result.failure("Invalid signature");

		} catch (ExpiredJwtException e) {
			return Result.failure("Expired token");
		} catch (MalformedJwtException e) {
			return Result.failure("The provided token is malformed!");

		} catch (JwtException e) {
			return Result.failure("The provided token is invalid!");
		} catch (Exception e) {
			return Result.failure("Unexpected error! Message: " + e.getMessage());
		}
	}
}

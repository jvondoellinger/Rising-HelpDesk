package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.ClaimsExceptionTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TokenPayloadFactory {
	private final ClaimsExceptionTranslator claimsExceptionTranslator;
	private final JwtExceptionsTranslator jwtExceptionsTranslator;
	private final ClaimsFactory claimsFactory;

	public Result<TokenPayload, String> fromEncodedToken(EncodedToken encodedToken) {
		var claimsResult = claimsFactory.factory(encodedToken);

		if (claimsResult.isFailure()) {
			return Result.failure(claimsResult.getError());
		}

		var claims = claimsResult.getValue();

		// JTI
		var jtiResult = parseUUID(claims.getId(), "Missing JTI on token!");
		if (jtiResult.isFailure()) return Result.failure(jtiResult.getError());

		// Subject
		var subjectResult = parseUUID(claims.getSubject(), "Subject JTI on token!");
		if (subjectResult.isFailure()) return Result.failure(subjectResult.getError());

		// Dates
		Date expiration = claims.getExpiration();
		Date issueAt = claims.getIssuedAt();

		var idsResult = parseUUIDList(claims.get("accessProfileIds"));
		if (idsResult.isFailure()) return Result.failure(idsResult.getError());

		var payload  =	 new TokenPayload(
			   jtiResult.getValue(),
			   subjectResult.getValue(),
			   idsResult.getValue(),
			   expiration,
			   issueAt
		);

		return Result.success(payload);
	}
	// ! Utils
	private Result<UUID, String> parseUUID(String value, String errorMessage) {
		if (value == null || value.isBlank()) {
			return Result.failure(errorMessage);
		}

		try {
			return Result.success(UUID.fromString(value));
		} catch (IllegalArgumentException e) {
			return Result.failure(errorMessage);
		}
	}
	private Result<List<UUID>, String> parseUUIDList(Object value) {
		if (!(value instanceof List<?> list) || list.isEmpty()) {
			return Result.failure("Invalid or missing accessProfileIds claim");
		}

		List<UUID> result = new ArrayList<>();

		for (Object item : list) {
			try {
				var id = UUID.fromString(String.valueOf(item));
				result.add(id);
			} catch (IllegalArgumentException e) {
				return Result.failure("Invalid UUID inside accessProfileIds");
			}
		}

		return Result.success(result);
	}
}

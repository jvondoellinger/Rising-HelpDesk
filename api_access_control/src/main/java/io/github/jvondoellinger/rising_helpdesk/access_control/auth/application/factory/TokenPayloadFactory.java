package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.ClaimsExceptionTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class TokenPayloadFactory {
	private final ClaimsExceptionTranslator claimsExceptionTranslator;
	private final JwtExceptionsTranslator jwtExceptionsTranslator;
	private final ClaimsFactory claimsFactory;

	public Result<TokenPayload> fromEncodedToken(EncodedToken encodedToken) {
		var claimsResult = claimsFactory.factory(encodedToken);

		if (claimsResult.isError()) {
			return Result.error(claimsResult.getError());
		}

		var claims = claimsResult.getValue();

		// JTI
		var jtiResult = parseUUID(claims.getId(), "Missing JTI on token!");
		if (jtiResult.isError()) return Result.error(jtiResult.getError());

		// Subject
		var subjectResult = parseUUID(claims.getSubject(), "Subject JTI on token!");
		if (subjectResult.isError()) return Result.error(subjectResult.getError());

		// Dates
		Date expiration = claims.getExpiration();
		Date issueAt = claims.getIssuedAt();

		var idsResult = parseUUIDList(claims.get("accessProfileIds"));
		if (idsResult.isError()) return Result.error(idsResult.getError());

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
	private Result<UUID> parseUUID(String value, String errorMessage) {
		if (value == null || value.isBlank()) {
			return Result.error(new DomainError("UNEXPECTED_ERROR", errorMessage));
		}

		try {
			return Result.success(UUID.fromString(value));
		} catch (IllegalArgumentException e) {
			return Result.error(new DomainError("UNEXPECTED_ERROR", errorMessage));
		}
	}
	private Result<List<UUID>> parseUUIDList(Object value) {
		if (!(value instanceof List<?> list) || list.isEmpty()) {
			return Result.error(new DomainError("INVALID_OR_MISSING_ACCESSPROFILEIDS_CLAIM", "Invalid or missing accessProfileIds claim"));
		}

		List<UUID> result = new ArrayList<>();

		for (Object item : list) {
			try {
				var id = UUID.fromString(String.valueOf(item));
				result.add(id);
			} catch (IllegalArgumentException e) {
				return Result.error(new DomainError("INVALID_UUID_INSIDE_ACCESSPROFILEIDS", "Invalid UUID inside accessProfileIds"));
			}
		}

		return Result.success(result);
	}
}

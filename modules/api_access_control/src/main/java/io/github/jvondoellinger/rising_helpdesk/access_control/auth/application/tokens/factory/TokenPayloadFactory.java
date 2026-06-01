package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator.ClaimsExceptionTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.shared.application.objectValues.Pair;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenPayloadFactory {
	private final ClaimsExceptionTranslator claimsExceptionTranslator;
	private final JwtExceptionsTranslator jwtExceptionsTranslator;
	private final ClaimsFactory claimsFactory;

	public ResultB<TokenPayload> fromEncodedToken(EncodedToken encodedToken) {
		return claimsFactory.factory(encodedToken)
			   .flatMap(claims ->
					 extractSubjectAndClaimId(claims)
						    .flatMap(pair -> {
							    Date expiration = claims.getExpiration();
							    Date issueAt = claims.getIssuedAt();

							    return parseUUIDList(claims.get("accessProfileIds"))
									  .map(ids -> {
										  return new TokenPayload(
												pair.getValue1().getId(),
												pair.getValue2().getId(),
												ids,
												expiration,
												issueAt
										  );
									  });
						    })
			   );
	}

	// ! Utils
	private ResultB<Pair<JtiId, SubjectId>> extractSubjectAndClaimId(Claims claims) {
		return parseUUID(claims.getId(), "Missing JTI on token!")
			   .flatMap(jti ->
					 parseUUID(claims.getSubject(), "Missing Subject on token!")
						    .map(subject -> {
							    var jtiId = new JtiId(jti);
							    var subjectId = new SubjectId(subject);
							    return new Pair<>(jtiId, subjectId);
						    })
			   );
	}

	private ResultB<UUID> parseUUID(String value, String errorMessage) {
		if (value == null || value.isBlank()) {
			return ResultB.error(new DomainError("UNEXPECTED_ERROR", errorMessage));
		}

		try {
			return ResultB.of(UUID.fromString(value));
		} catch (IllegalArgumentException e) {
			return ResultB.error(new DomainError("UNEXPECTED_ERROR", errorMessage));
		}
	}

	private ResultB<List<UUID>> parseUUIDList(Object value) {
		if (!(value instanceof List<?> list) || list.isEmpty()) {
			return ResultB.error(new DomainError("INVALID_OR_MISSING_ACCESSPROFILEIDS_CLAIM", "Invalid or missing accessProfileIds claim"));
		}

		List<UUID> result = new ArrayList<>();

		for (Object item : list) {
			try {
				var id = UUID.fromString(String.valueOf(item));
				result.add(id);
			} catch (IllegalArgumentException e) {
				return ResultB.error(new DomainError("INVALID_UUID_INSIDE_ACCESSPROFILEIDS", "Invalid UUID inside accessProfileIds"));
			}
		}

		return ResultB.of(result);
	}

	private static abstract class InternalTokenIds {
		private final UUID id;

		protected InternalTokenIds(UUID id) {
			this.id = id;
		}

		public UUID getId() {
			return id;
		}
	}

	private static class JtiId extends InternalTokenIds {
		protected JtiId(UUID id) {
			super(id);
		}
	}

	private static class SubjectId extends InternalTokenIds {

		protected SubjectId(UUID id) {
			super(id);
		}
	}
}

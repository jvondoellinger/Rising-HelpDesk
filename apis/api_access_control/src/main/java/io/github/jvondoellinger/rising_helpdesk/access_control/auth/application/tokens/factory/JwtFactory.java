package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.secretKey.ApiSecretKey;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class JwtFactory {
	private static final String accessProfileIdsClaimName = "access_profile_ids";
	private final JwtExceptionsTranslator translator;
	private final ApiSecretKey secretKey;

	// Methods
	public Result<String> factory(TokenPayload payload) {
		if (!payload.getExpiration().after(new Date())) {
			return Result.error(new DomainError("EXPIRATION_EQUAL_TO_CURRENT_TIME", "Expiration equal to current time."));
		}

		return translator.translate(() -> {
			var accessProfileIds = payload
				   .getAccessProfileIds()
				   .stream()
				   .map(UUID::toString)
				   .toString();

			return Jwts
				   .builder()
				   .id(payload.getJti().toString())
				   .subject(payload.getSubject().toString())
				   .claim(accessProfileIdsClaimName, accessProfileIds)
				   .issuedAt(payload.getIssueAt())
				   .expiration(payload.getExpiration())
				   .signWith(secretKey.getCurrent())
				   .compact();
		});
	}

	// Methods
	public Result<String> factory() {
		return translator.translate(() -> {
			var expiration = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
			return Jwts
				   .builder()
				   .id(UUID.randomUUID().toString())
				   .signWith(secretKey.getCurrent())
				   .expiration(expiration)
				   .compact();
		});
	}
}

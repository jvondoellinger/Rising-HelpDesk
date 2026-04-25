package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.ApiSecretKey;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JwtFactory {
	private static final String accessProfileIdsClaimName = "access_profile_ids";
	private final JwtExceptionsTranslator translator;
	private final ApiSecretKey secretKey;

	// Methods
	public ResultV1<String, String> factory(TokenPayload payload) {
		if (!payload.getExpiration().after(new Date())) {
			return ResultV1.failure("Expiration equal to current time.");
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
}

package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.DecodedToken;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class TokenService {
	private static final SecretKey secretKey = Jwts.SIG.HS512.key().build();

	public EncodedToken encode(DecodedToken decodedToken) {
		// Mapping List<UUID> to List<String>
		var accessProfileIds = decodedToken
			   .getAccessProfileIds()
			   .stream()
			   .map(UUID::toString)
			   .toString();

		// Creating JWT token
		var jwt = Jwts
			   .builder()
			   .subject(decodedToken.getSubject().toString())
			   .claim("accessProfileIds", accessProfileIds)
			   .issuedAt(decodedToken.getIssueAt())
			   .expiration(decodedToken.getExpiration())
			   .signWith(secretKey)
			   .compact();
		return new EncodedToken(jwt);
	}

	@FixAfter
	public DecodedToken decode(EncodedToken encodedToken) {
		Object unsecureClaims = Jwts.parser()
			   .verifyWith(secretKey)
			   .build()
			   .parse(encodedToken.toString())
			   .getPayload();
		UUID subject = null;
		Date expiration = null;
		Date issueAt = null;
		List<UUID> ids = new ArrayList<>();

		if (unsecureClaims instanceof Claims claims) {
			if (claims.get("accessProfileIds") instanceof List<?> list) {
				var s = (List<String>) list;
				var items = s
					   .stream()
					   .map(UUID::fromString)
					   .toList();
				ids.addAll(items);
			}

			subject = UUID.fromString(claims.getSubject());
			issueAt = claims.getIssuedAt();
			expiration = claims.getExpiration();
		}

		boolean invalid = Objects.isNull(subject) ||
			   Objects.isNull(issueAt) ||
			   Objects.isNull(expiration) ||
			   ids.isEmpty();

		if (invalid) {
			return null;
		}

		return new DecodedToken(
			   encodedToken.toString(),
			   subject,
			   ids,
			   expiration,
			   issueAt
		);
	}
}

package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.*;

@SuppressWarnings("unchecked")
@Service
public class TokenPayloadMapper {
	public TokenPayload map(Claims claims) {
		var jtiString = claims.getId();

		if (Objects.isNull(jtiString) || jtiString.isBlank()){
			throw new IllegalArgumentException("Invalid Token! JTI is missing!");
		}

		UUID jti = UUID.fromString(jtiString);
		UUID subject = UUID.fromString(claims.getSubject());
		Date expiration = claims.getExpiration();
		Date issueAt = claims.getIssuedAt();

		List<String> claimIds = (List<String>) claims.get("accessProfileIds", List.class);
		List<UUID> items = claimIds
			   .stream()
			   .map(UUID::fromString)
			   .toList();
		List<UUID> ids = new ArrayList<>(items);

		boolean invalid = Objects.isNull(issueAt) ||
			   Objects.isNull(expiration) ||
			   ids.isEmpty();

		if (invalid) {
			throw new IllegalArgumentException("The provided token is invalid!");
		}

		return new TokenPayload(
			   jti,
			   subject,
			   ids,
			   expiration,
			   issueAt
		);
	}
}

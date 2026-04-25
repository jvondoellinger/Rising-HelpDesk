package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.*;

@SuppressWarnings("unchecked")
@Service
@Deprecated
public class TokenPayloadMapper {
	public ResultV1<TokenPayload, String> map(Claims claims) {
		var jtiString = claims.getId();

		if (Objects.isNull(jtiString) || jtiString.isBlank()){
			return ResultV1.failure("Invalid Token! JTI is missing!");
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

		return ResultV1.success(new TokenPayload(
			   jti,
			   subject,
			   ids,
			   expiration,
			   issueAt
		));
	}
}

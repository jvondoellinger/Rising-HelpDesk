package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenContent;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.*;

@Service
public class TokenService {
	private static final SecretKey secretKey = Jwts.SIG.HS512.key().build();

	public Result<EncodedToken> encode(TokenContent tokenContent) {
		try {
			// Mapping List<UUID> to List<String>
			var accessProfileIds = tokenContent
				   .getAccessProfileIds()
				   .stream()
				   .map(UUID::toString)
				   .toString();

			// Creating JWT token
			var jwt = Jwts
				   .builder()
				   .subject(tokenContent.getSubject().toString())
				   .claim("accessProfileIds", accessProfileIds)
				   .issuedAt(tokenContent.getIssueAt())
				   .expiration(tokenContent.getExpiration())
				   .signWith(secretKey)
				   .compact();

			var encodedToken = new EncodedToken(jwt);

			return Result.success(encodedToken);
		} catch (io.jsonwebtoken.security.SignatureException e) {
			return Result.failure("Invalid signature");

		} catch (ExpiredJwtException e) {
			return Result.failure("Expired token");

		} catch (JwtException e) {
			return Result.failure("The provided token is invalid!");

		}

	}

	@FixAfter
	public Result<TokenContent> decode(EncodedToken encodedToken) {
		try {
			Claims claims = Jwts.parser()
				   .verifyWith(secretKey)
				   .build()
				   .parseSignedClaims(encodedToken.toString())
				   .getPayload();
			UUID subject = null;
			Date expiration = null;
			Date issueAt = null;
			List<UUID> ids = new ArrayList<>();

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


			boolean invalid = Objects.isNull(subject) ||
				   Objects.isNull(issueAt) ||
				   Objects.isNull(expiration) ||
				   ids.isEmpty();

			if (invalid) {
				return Result.failure("The provided token is invalid!");
			}
			var content = new TokenContent(
				   subject,
				   ids,
				   expiration,
				   issueAt
			);

			return Result.success(content);
		} catch (SignatureException e) {
			return Result.failure("Invalid signature");

		} catch (ExpiredJwtException e) {
			return Result.failure("Expired token");
		} catch (MalformedJwtException e) {
			return Result.failure("The provided token is malformed!");

		} catch (JwtException e) {
			return Result.failure("The provided token is invalid!");
		}
	}
}

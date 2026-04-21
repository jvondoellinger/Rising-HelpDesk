package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.ApiSecretKey;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.TokenPayloadMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator.JwtExceptionsTranslator;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.utils.JwtTokenFormatter.formatToken;

@Service
@AllArgsConstructor
public class JwtTokenService implements TokenService {
	// Dependency Injection
	private final StringRedisTemplate template;
	private final ApiSecretKey apiSecretKey;
	private final JwtExceptionsTranslator jwtExceptionTranslator;
	private final JtiKeyFactory keyFactory;
	private final TokenPayloadMapper tokenPayloadMapper;

	// Claim name (to avoid typos)
	private static final String accessProfileIdsClaimName = "access_profile_ids";

	@Override
	public Result<EncodedToken> generate(TokenPayload tokenPayload) {
		return jwtExceptionTranslator.translate(() -> {
			// Mapping List<UUID> to List<String>
			var accessProfileIds = tokenPayload
				   .getAccessProfileIds()
				   .stream()
				   .map(UUID::toString)
				   .toString();

			// Creating JWT token
			var jwt = Jwts
				   .builder()
				   .id(tokenPayload.getJti().toString())
				   .subject(tokenPayload.getSubject().toString())
				   .claim(accessProfileIdsClaimName, accessProfileIds)
				   .issuedAt(tokenPayload.getIssueAt())
				   .expiration(tokenPayload.getExpiration())
				   .signWith(apiSecretKey.getCurrent())
				   .compact();

			var encodedToken = new EncodedToken(jwt);

			return Result.success(encodedToken);
		});
	}
	@Override
	public Result<TokenPayload> verify(EncodedToken encodedToken) {
		return jwtExceptionTranslator.translate(() -> {
			String token = formatToken(encodedToken.toString());

			Claims claims = Jwts.parser()
				   .verifyWith(apiSecretKey.getCurrent())
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();

			var revokedResult = isRevoked(UUID.fromString(claims.getId()));
			if (revokedResult.isFailure()) {
				return Result.failure("We were unable to verify if the token has been revoked!");
			}

			if (revokedResult.getValue()) { // Are revoked?
				return Result.failure("Token revoked!");
			}

			var payload = tokenPayloadMapper.map(claims);
			return Result.success(payload);
		});
	}
	@Override
	public Result<TokenPayload> revoke(EncodedToken token) {
		return jwtExceptionTranslator.translate(() -> {
			Claims claims = Jwts.parser()
				   .verifyWith(apiSecretKey.getCurrent())
				   .build()
				   .parseSignedClaims(formatToken(token.toString()))
				   .getPayload();

			var payload = tokenPayloadMapper.map(claims);
			var jti = payload.getJti();
			var key = keyFactory.getJtiRevokedKey(payload.getSubject());
			var jtis = template.opsForValue().get(key);

		});
	}
	@Override
	public Result<TokenPayload> revokeAll(UUID userId) {
		return null;
	}

	@Override
	public Result<Boolean> isRevoked(EncodedToken encodedToken) {
		return null;
	}

	@Override
	public Result<Boolean> isRevoked(UUID jti) {
		return null;
	}

	@Override
	public Result<Boolean> canIssueNewToken(UUID userId) {
		return null;
	}

	@Override
	public Result<List<TokenPayload>> getAllActiveJtisByUserId(UUID userId) {
		return null;
	}

	@Override
	public Result<Integer> getActiveTokensCount(UUID userId) {
		return null;
	}

	@Override
	public Result<List<TokenPayload>> getActiveSessions(UUID userId) {
		return null;
	}



}

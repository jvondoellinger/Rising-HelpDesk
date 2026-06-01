package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory.JwtFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.shared.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

// Adicionar um mapper que vai retornar ResultA<> e fazer toda conversão do jwt para o payload ou vice-versa, assim permitindo que o código fique mais limpo e leigivel!


@AllArgsConstructor
@FixAfter
public class JwtTokenServiceImpl implements TokenService {
	private final JwtFactory jwtFactory;
	private final TokenPayloadFactory payloadFactory;
	private final StringRedisTemplate template;
	private final JtiKeyFactory keyFactory;

	@Override
	public ResultB<EncodedToken> generate(TokenPayload payload) {
		ResultB<String> jwtResult = jwtFactory.factory(payload);

		if (jwtResult.hasErrors()) {
			return jwtResult.boxingError();
		}

		var jwt = jwtResult.getOrDefault("");
		var encodedToken = new EncodedToken(jwt);

		return ResultB.of(encodedToken);
	}

	@Override
	public ResultB<EncodedToken> generate() {
		return jwtFactory.factory()
			   .map(token -> {
				   var encodedToken = new EncodedToken(token);
				   return encodedToken;
			   });
	}

	@Override
	public ResultB<TokenPayload> verify(EncodedToken encodedToken) {
		return payloadFactory.fromEncodedToken(encodedToken)
			   .flatMap(payload -> {

				   return isRevoked(payload.getJti(), payload.getSubject())
						 .mapIfError(x -> new DomainError("WE_WERE_UNABLE_TO_VERIFY_IF_THE_TOKEN_HAS_BEEN_REVOKED", "We were unable to verify if the token has been revoked!"))
						 .flatMap(revoked -> ResultB.error(new DomainError("TOKEN_REVOKED", "Token revoked!")));
			   });
	}
	@Override
	public ResultB<Void> revoke(EncodedToken token) {
		return payloadFactory.fromEncodedToken(token)
			   .flatMap(payload -> {
				   var userId = payload.getSubject();
				   var jti = payload.getJti().toString();

				   // Check duration
				   if (payload.isExpired()) return ResultB.error(new DomainError("EXPIRED_TOKEN", "Expired token!"));

				   // Redis keys
				   var revokeKey = keyFactory.getJtiRevokedKey(userId);
				   var activeKey = keyFactory.getJtiKey(userId);

				   // Removing jti from active tokens
				   template.opsForSet().remove(activeKey, jti);

				   // Including jti in revoked tokens
				   template.opsForSet().add(revokeKey, jti);
				   template.expire(revokeKey, payload.getRemainTime());

				   return ResultB.of(null);
			   });
	}
	@Override
	public ResultB<Void> revokeAll(UUID userId) {
		return ResultB.of(null)
			   .flatMap(aVoid -> {
				   var revokeKey = keyFactory.getJtiRevokedKey(userId);
				   var activeKey = keyFactory.getJtiKey(userId);

				   // Getting JTIs in Set<>
				   var jtisSet = template.opsForSet().members(activeKey);

				   if (Objects.isNull(jtisSet) || jtisSet.isEmpty()) {
					   return ResultB.error(new DomainError("NO_ACTIVE_TOKENS_REGISTERED", "No active tokens registered!"));
				   }

				   // Removing jti from active tokens
				   template.delete(activeKey);

				   // Including jti in revoked tokens
				   template.opsForSet().add(revokeKey, jtisSet.toArray(new String[0]));
				   template.expire(revokeKey, Duration.ofHours(24));

				   return ResultB.of(null);
			   });

	}
	@Override
	public ResultB<Boolean> isRevoked(EncodedToken encodedToken) {
		return payloadFactory.fromEncodedToken(encodedToken)
			   .map(payload -> {
				   var jti = payload.getJti();
				   var userId = payload.getSubject();
				   var key = keyFactory.getJtiRevokedKey(userId);

				   return template
						 .opsForSet()
						 .isMember(key, jti);
			   });
	}
	@Override
	public ResultB<Boolean> isRevoked(UUID jti, UUID userId) {
		var key = keyFactory.getJtiRevokedKey(userId);

		var isMember = template
			   .opsForSet()
			   .isMember(key, jti);

		return ResultB.of(isMember);
	}
	@Override
	public ResultB<Long> countJtiByUser(UUID userId) {
		var key = keyFactory.getJtiKey(userId);
		var size = template.opsForSet().size(key);

		return ResultB.of(size);
	}
}


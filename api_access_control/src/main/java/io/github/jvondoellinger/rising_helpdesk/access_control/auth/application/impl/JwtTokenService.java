package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.config.AuthenticationSettings;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JwtFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

// Adicionar um mapper que vai retornar Result<> e fazer toda conversão do jwt para o payload ou vice-versa, assim permitindo que o código fique mais limpo e leigivel!

@Service
@AllArgsConstructor
@FixAfter
public class JwtTokenService implements TokenService {
	private final JwtFactory jwtFactory;
	private final TokenPayloadFactory payloadFactory;
	private final StringRedisTemplate template;
	private final JtiKeyFactory keyFactory;
	private final AuthenticationSettings settings;

	@Override
	public Result<EncodedToken, String> generate(TokenPayload payload) {
		Result<String, String> jwtResult = jwtFactory.factory(payload);

		if (jwtResult.isFailure()) {
			return jwtResult.cast();
		}

		var countResult = countJtiByUser(payload.getSubject());
		if (countResult.isFailure()) {
			return countResult.cast();
		}
		if (countResult.getValue() > settings.getMaxTokensPerUser()) {
			return Result.failure("Maximum number of tokens reached per user!");
		}

		var jwt = jwtResult.getValue();
		var encodedToken = new EncodedToken(jwt);

		return Result.success(encodedToken);
	}
	@Override
	public Result<TokenPayload, String> verify(EncodedToken encodedToken) {
		// Payload Result
		var payloadResult = payloadFactory.fromEncodedToken(encodedToken);
		if (payloadResult.isFailure()) return payloadResult;

		// Payload Value
		var payload = payloadResult.getValue();

		// Revoke Result
		var revokedResult = isRevoked(payload.getJti(), payload.getSubject());
		if (revokedResult.isFailure()) return Result.failure("We were unable to verify if the token has been revoked!");

		// Revoke Value
		var isRevoked = revokedResult.getValue();
		if (isRevoked) return Result.failure("Token revoked!");

		return Result.success(payload);
	}
	@Override
	public Result<Void, String> revoke(EncodedToken token) {
		// Payload Result
		var payloadResult = payloadFactory.fromEncodedToken(token);
		if (payloadResult.isFailure()) return Result.failure(payloadResult.getError());

		// Arrange
		var payload = payloadResult.getValue();
		var userId = payload.getSubject();
		var jti = payload.getJti().toString();

		// Check duration
		var duration = Duration.between(Instant.now(), payload.getExpiration().toInstant());
		if (duration.isNegative() || duration.isZero()) return Result.failure("Expired token!");

		// Redis keys
		var revokeKey = keyFactory.getJtiRevokedKey(userId);
		var activeKey = keyFactory.getJtiKey(userId);

		// Removing jti from active tokens
		template.opsForSet().remove(activeKey, jti);

		// Including jti in revoked tokens
		template.opsForSet().add(revokeKey, jti);
		template.expire(revokeKey, duration);

		return Result.success();
	}
	@Override
	public Result<Void, String> revokeAll(UUID userId) {
		var revokeKey = keyFactory.getJtiRevokedKey(userId);
		var activeKey = keyFactory.getJtiKey(userId);

		// Getting JTIs in Set<>
		var jtisSet = template.opsForSet().members(activeKey);

		if (Objects.isNull(jtisSet) || jtisSet.isEmpty()) {
			return Result.failure("No active tokens registered!");
		}

		// Removing jti from active tokens
		template.delete(activeKey);

		// Including jti in revoked tokens
		template.opsForSet().add(revokeKey, jtisSet.toArray(new String[0]));
		template.expire(revokeKey, Duration.ofHours(24));

		return Result.success();
	}
	@Override
	public Result<Boolean, String> isRevoked(EncodedToken encodedToken) {
		var payloadResult = payloadFactory.fromEncodedToken(encodedToken);

		if (payloadResult.isFailure()) {
			return Result.failure(payloadResult.getError());
		}

		var payload = payloadResult.getValue();
		var jti = payload.getJti();
		var userId = payload.getSubject();
		var key = keyFactory.getJtiRevokedKey(userId);

		var isMember = template
			   .opsForSet()
			   .isMember(key, jti);

		return Result.success(isMember);
	}
	@Override
	public Result<Boolean, String> isRevoked(UUID jti, UUID userId) {
		var key = keyFactory.getJtiRevokedKey(userId);

		var isMember = template
			   .opsForSet()
			   .isMember(key, jti);

		return Result.success(isMember);
	}
	@Override
	public Result<Long, String> countJtiByUser(UUID userId) {
		var key = keyFactory.getJtiKey(userId);
		var size = template.opsForSet().size(key);

		return Result.success(size);
	}
}
/*
	Ela deveria somente validar os tokens e agora também está validando sessão?

	Qual seria um fluxo melhor?

	JwtTokenService -> Verificar o Token, salvar no redis os tokens active e direcionar os tokens revogados para blacklist
	SessionService retornar uma sessão ao cliente. Vai salvar o token e revolgar (o token e a sessão fariam parte da mesma regra)

	referenciar o jti no session data - ex: session:data:jti:{jwt} e insiro os dados da sessão.

*/


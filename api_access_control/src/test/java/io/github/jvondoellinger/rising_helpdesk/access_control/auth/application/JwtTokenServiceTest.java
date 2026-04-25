package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl.JwtTokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtTokenServiceTest {

	@Mock
	private JwtTokenService jwtTokenService;
	private SecretKey secretKey;

	@BeforeEach
	void setUp() throws Exception {
		secretKey = readSecretKey();
	}

	@Test
	@DisplayName("encode deve retornar JWT compacto com três segmentos")
	void encode_shouldReturnCompactJwt() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(30));
		var expiration = Date.from(Instant.now().plusSeconds(3600));
		var version = 1L;
		var decoded = new TokenPayload(subject, List.of(profileId), expiration, issuedAt, version);

		var encoded = jwtTokenService.generate(decoded);

		assertThat(encoded).isNotNull();
		assertThat(encoded.toString()).isNotBlank();
		assertThat(encoded.toString().split("\\.")).hasSize(3);
	}

	@Test
	@DisplayName("encode deve incluir subject e datas verificáveis no JWT")
	void encode_shouldEmbedSubjectAndDates() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = new Date((Instant.now().minusSeconds(10).getEpochSecond()) * 1000);
		var expiration = new Date((Instant.now().plusSeconds(7200).getEpochSecond()) * 1000);
		var version = 1L;
		var decoded = new TokenPayload(subject, List.of(profileId), expiration, issuedAt, version);

		var encoded = jwtTokenService.generate(decoded);

		var claims = parseClaims(encoded.getValue().toString());
		assertThat(claims.getSubject()).isEqualTo(subject.toString());
		assertThat(claims.getIssuedAt()).isEqualTo(issuedAt);
		assertThat(claims.getExpiration()).isEqualTo(expiration);
	}

	@Test
	@DisplayName("decode deve reconstruir subject, perfis e datas quando o claim accessProfileIds for lista")
	void decode_shouldReturnDecodedTokenWhenClaimsAreValid() {
		var subject = UUID.randomUUID();
		var profileOne = UUID.randomUUID();
		var profileTwo = UUID.randomUUID();
		var issuedAt = new Date((Instant.now().minusSeconds(120).getEpochSecond()) * 1000);
		var expiration = new Date((Instant.now().plusSeconds(1800).getEpochSecond()) * 1000);

		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.of(profileOne.toString(), profileTwo.toString()))
			   .issuedAt(issuedAt)
			   .expiration(expiration)
			   .signWith(secretKey)
			   .compact();

		var result = jwtTokenService
			   .decode(new EncodedToken(jwt))
			   .getValue();

		assertThat(result).isNotNull();
		assertThat(result.getSubject()).isEqualTo(subject);
		assertThat(result.getAccessProfileIds()).containsExactly(profileOne, profileTwo);
		assertThat(result.getIssueAt()).isEqualTo(issuedAt);
		assertThat(result.getExpiration()).isEqualTo(expiration);
	}

	@Test
	@DisplayName("decode deve retornar null quando accessProfileIds não for lista")
	void decode_shouldReturnNullWhenAccessProfileIdsClaimIsNotList() {
		var subject = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(5));
		var expiration = Date.from(Instant.now().plusSeconds(600));

		var jwt = Jwts
			   .builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", "not-a-list")
			   .issuedAt(issuedAt)
			   .expiration(expiration)
			   .signWith(secretKey)
			   .compact();

		assertThat(jwtTokenService.decode(new EncodedToken(jwt)).isError()).isTrue();
	}

	@Test
	@DisplayName("decode deve retornar null quando a lista de perfis estiver vazia")
	void decode_shouldReturnNullWhenAccessProfileIdsListIsEmpty() {
		var subject = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(5));
		var expiration = Date.from(Instant.now().plusSeconds(600));
		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.<String>of())
			   .issuedAt(issuedAt)
			   .expiration(expiration)
			   .signWith(secretKey)
			   .compact();

		assertThat(jwtTokenService.decode(new EncodedToken(jwt)).isError()).isTrue();
	}

	@Test
	@DisplayName("decode deve retornar null quando issuedAt estiver ausente")
	void decode_shouldReturnNullWhenIssuedAtMissing() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var expiration = Date.from(Instant.now().plusSeconds(900));
		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.of(profileId.toString()))
			   .expiration(expiration)
			   .signWith(secretKey)
			   .compact();

		assertThat(jwtTokenService.decode(
					 new EncodedToken(jwt)
			   ).isError()
		).isTrue();
	}

	@Test
	@DisplayName("decode deve retornar null quando expiration estiver ausente")
	void decode_shouldReturnNullWhenExpirationMissing() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(5));
		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.of(profileId.toString()))
			   .issuedAt(issuedAt)
			   .signWith(secretKey)
			   .compact();

		assertThat(jwtTokenService.decode(new EncodedToken(jwt)).isError()).isTrue();
	}

	@Test
	@DisplayName("decode deve lançar exceção quando o token estiver malformado")
	void decode_shouldThrowWhenTokenMalformed() {
		assertThat(jwtTokenService.decode(new EncodedToken("a.b.c")).isError())
			   .isTrue();
	}

	@Test
	@DisplayName("decode deve lançar exceção quando a assinatura for inválida")
	void decode_shouldThrowWhenSignatureInvalid() {
		var otherKey = Jwts.SIG.HS512.key().build();
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(5));
		var expiration = Date.from(Instant.now().plusSeconds(600));
		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.of(profileId.toString()))
			   .issuedAt(issuedAt)
			   .expiration(expiration)
			   .signWith(otherKey)
			   .compact();

		assertThat(jwtTokenService.decode(new EncodedToken(jwt))
			   .isError())
			   .isTrue();
	}

	@Test
	@DisplayName("decode deve lançar exceção quando o token estiver expirado")
	void decode_shouldThrowWhenTokenExpired() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(3600));
		var expiration = Date.from(Instant.now().minusSeconds(60));
		var jwt = Jwts.builder()
			   .subject(subject.toString())
			   .claim("accessProfileIds", List.of(profileId.toString()))
			   .issuedAt(issuedAt)
			   .expiration(expiration)
			   .signWith(secretKey)
			   .compact();

		assertThat(jwtTokenService.decode(new EncodedToken(jwt)).isError())
			   .isTrue();
	}

	@Test
	@DisplayName("decode após encode retorna null")
	void decode_afterEncode_returnsNull() {
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(5));
		var expiration = Date.from(Instant.now().plusSeconds(900));
		var version = 1L;
		var decoded = new TokenPayload(subject, List.of(profileId), expiration, issuedAt, version);

		var encoded = jwtTokenService.generate(decoded).getValue();

		assertThat(jwtTokenService.decode(encoded).isError()).isTrue();
	}

	@Test
	@DisplayName("decode deve retornar um erro por conta do token revogado")
	void decode_shouldReturnErrorRevokedToken() {
		// Arrange
		var subject = UUID.randomUUID();
		var profileId = UUID.randomUUID();
		var issuedAt = Date.from(Instant.now().minusSeconds(30));
		var expiration = Date.from(Instant.now().plusSeconds(3600));
		var version = 1L;

		var payload = new TokenPayload(subject, List.of(profileId), expiration, issuedAt, version);


		// Act
		var encodedResult = jwtTokenService.generate(payload);
		assertThat(encodedResult.isSuccess()).isTrue();

		var value = encodedResult.getValue();

		var revoke = jwtTokenService.revoke(value);
		var verify = jwtTokenService.verify(value);

		// Assert
		assertThat(revoke.isSuccess()).isTrue();
		assertThat(verify.isError()).isTrue();
	}

	private Claims parseClaims(String jwt) {
		return Jwts.parser()
			   .verifyWith(secretKey)
			   .build()
			   .parseSignedClaims(jwt)
			   .getPayload();
	}

	private static SecretKey readSecretKey() throws Exception {
		Field field = JwtTokenService.class.getDeclaredField("secretKey");
		field.setAccessible(true);
		return (SecretKey) field.get(null);
	}
}

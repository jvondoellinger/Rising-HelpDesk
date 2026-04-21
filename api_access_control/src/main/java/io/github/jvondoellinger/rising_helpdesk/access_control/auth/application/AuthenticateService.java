package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticateService
	   implements PrefixCreatorTemplate {

	private final StringRedisTemplate template;
	private static final String prefix = "user:%s:tokens";

	public boolean validateToken(TokenPayload content) {
		var token = template
			   .opsForValue()
			   .get(createPrefix(content.getSubject().toString()));

		return token != null && !token.isBlank();
	}


	@Override
	public String createPrefix(String data) {
		return prefix.formatted(data);
	}
}

package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.config;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.factory.JtiKeyFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory.JwtFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.factory.TokenPayloadFactory;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl.JwtTokenServiceImpl;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.decorators.MaxTokensPerUser;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.settings.TokenSettings;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@AllArgsConstructor
public class TokenServiceConfig {
	private final JwtFactory jwtFactory;
	private final TokenPayloadFactory payloadFactory;
	private final StringRedisTemplate template;
	private final JtiKeyFactory keyFactory;
	private final TokenSettings settings;

	@Bean
	public TokenService tokenService() {
		var serviceImpl = new JwtTokenServiceImpl(jwtFactory, payloadFactory, template, keyFactory);
		var maxPerUser = new MaxTokensPerUser(serviceImpl, settings); // last decorator

		return maxPerUser;
	}
}

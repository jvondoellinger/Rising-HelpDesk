package io.github.jvondoellinger.rising_helpdesk.config;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware.JwtAuthFilter;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AuthConfig {
	@Bean
	public JwtAuthFilter jwtAuthFilter(TokenService service) {
		return new JwtAuthFilter(service);
	}
}

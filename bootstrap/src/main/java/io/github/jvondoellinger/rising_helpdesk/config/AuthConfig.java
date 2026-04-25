package io.github.jvondoellinger.rising_helpdesk.config;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware.JwtAuthFilter;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.AuthenticateService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.impl.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtTokenService service, AuthenticateService authenticateService, JwtTokenService jwtTokenService) {
		return new JwtAuthFilter(service, authenticateService, jwtTokenService);
	}
}

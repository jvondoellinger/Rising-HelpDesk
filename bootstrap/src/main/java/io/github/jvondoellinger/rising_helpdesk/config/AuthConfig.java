package io.github.jvondoellinger.rising_helpdesk.config;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware.JwtAuthFilter;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl.JwtTokenServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtTokenServiceImpl service, AuthenticationPipeline authenticationPipeline, JwtTokenServiceImpl jwtTokenService) {
		return new JwtAuthFilter(service, authenticationPipeline, jwtTokenService);
	}
}

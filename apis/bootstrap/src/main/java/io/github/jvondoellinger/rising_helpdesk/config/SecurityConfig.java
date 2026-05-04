package io.github.jvondoellinger.rising_helpdesk.config;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
		return http
			   .csrf(AbstractHttpConfigurer::disable)
			   .authorizeHttpRequests(auth -> auth
					 .requestMatchers("/public/**")
					 .permitAll()
					 .anyRequest()
					 .authenticated()
			   )
			   .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			   .build();
	}
}

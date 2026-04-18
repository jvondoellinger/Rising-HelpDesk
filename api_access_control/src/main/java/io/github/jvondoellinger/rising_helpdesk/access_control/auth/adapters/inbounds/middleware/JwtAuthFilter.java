package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.AuthenticateService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final TokenService service;
	private final AuthenticateService authenticateService;
	private final TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = request.getHeader("Authorization");

		if (Objects.isNull(token)) {
			unauthorize(response);
			return;
		}

		var result = tokenService.decode(new EncodedToken(token));

		if (result.isFailure()) {
			unauthorize(response);
			return;
		}

		var content = result.getValue();
		var valid = authenticateService.validateToken(content);

		if (!valid) {
			unauthorize(response);
		}

		filterChain.doFilter(request, response);
	}

	private void unauthorize(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Invalid or corrupted token");
	}
}

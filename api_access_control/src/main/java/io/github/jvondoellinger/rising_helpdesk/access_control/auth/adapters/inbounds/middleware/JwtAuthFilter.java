package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.impl.JwtTokenServiceImpl;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.EncodedToken;
import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtTokenServiceImpl service;
	private final AuthenticationPipeline authenticationPipeline;
	private final JwtTokenServiceImpl jwtTokenService;

	@Override
	@FixAfter // Validar se o fluxo vai funcionar corretamente
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = request.getHeader("Authorization");

		if (Objects.isNull(token)) {
			// unauthorize(response);
			// Não precisa negar, pois o Spring *DEVE* cuidar disso!
			filterChain.doFilter(request, response);
			return;
		}

		var result = jwtTokenService.verify(new EncodedToken(token));

		if (result.isError()) {
			unauthorize(response);
			return;
		}

		var content = result.getValue();
		var valid = authenticationPipeline.validateToken(content);

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

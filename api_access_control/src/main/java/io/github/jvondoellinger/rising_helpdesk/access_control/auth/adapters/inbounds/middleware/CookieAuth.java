package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds.middleware;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
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
public class CookieAuth extends OncePerRequestFilter {
	private final TokenService service;

	@Override
	@FixAfter // Validar se o fluxo vai funcionar corretamente
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var header = request.getHeader("Authorization");

		if (Objects.isNull(header)) {
			// unauthorize(response);
			// Não precisa negar, pois o Spring *DEVE* cuidar disso!
			filterChain.doFilter(request, response);
			return;
		}

		var result = service.verify(new EncodedToken(token));
		if (result.isError()) {
			unauthorize(response);
			return;
		}
		var content = result.getValue();

		filterChain.doFilter(request, response);
	}

	private void unauthorize(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Invalid or corrupted token");
	}
}

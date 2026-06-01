package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class TokenController {
	private final TokenService service;

	@GetMapping("/guest")
	public ResultB<?> authorizeGuestClient(HttpServletResponse response) {
		return ResultB.empty()
			   .flatMap(aVoid -> service.generate())
			   .map(token -> {
				   var cookie = new Cookie("Token", token.toString());
				   cookie.setHttpOnly(true);
				   cookie.setSecure(true);
				   cookie.setPath("/");
				   response.addCookie(cookie);

				   return "Guest Client has been registered";
			   });
	}

	@GetMapping("/login")
	public ResultB<?> login() {
		return service.generate();
	}
}

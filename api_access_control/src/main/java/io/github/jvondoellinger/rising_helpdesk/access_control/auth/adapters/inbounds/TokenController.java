package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
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
	public Result<?> authorizeGuestClient(HttpServletResponse response) {
		var tokenResult = ResultTransformerStep.from(service.generate())
			   .flatMap(x -> Result.success(x.getValue().toString()))
			   .then();

		if (tokenResult.isError()) {
			return tokenResult;
		}

		var cookie = new Cookie("Token", tokenResult.getValue());
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		return Result.success("Guest Client has been registered");
	}
	@GetMapping("/login")
	public Result<?> login() {
		return service.generate();
	}
}

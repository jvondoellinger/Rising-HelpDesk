package io.github.jvondoellinger.rising_helpdesk.access_control.auth.adapters.inbounds;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.services.TokenService;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
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
	public Result<?> getGuestToken() {
		return ResultTransformerStep.from(service.generate())
			   .flatMap(x -> Result.success(x.getValue().toString()))
			   .then();
	}
	@GetMapping("/login")
	public Result<?> login() {
		return service.generate();
	}
}

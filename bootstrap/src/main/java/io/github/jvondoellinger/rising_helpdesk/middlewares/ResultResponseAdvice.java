package io.github.jvondoellinger.rising_helpdesk.middlewares;

import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return Result.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	@FixAfter
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof Result<?> result) {
			if (result.isSuccess()) {
				return result.getValue();
			}

			// Arrumar posteriormente esse trecho de código!
			var error = result.getError();
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return error;
		}

		// Case isn't Result<>
		return body;
	}
}

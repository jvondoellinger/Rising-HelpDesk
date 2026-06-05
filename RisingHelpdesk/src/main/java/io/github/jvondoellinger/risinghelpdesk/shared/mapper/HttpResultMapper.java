package io.github.jvondoellinger.risinghelpdesk.shared.mapper;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import org.springframework.http.ResponseEntity;

public final class HttpResultMapper {
	public static <T> ResponseEntity<?> from(ResultB<T> result) {
		var pair = result.get();

		if (pair.getValue2() != null) {
			return ResponseEntity
				   .badRequest()
				   .body(pair.getValue2().toString());
		}

		return ResponseEntity.ok(pair.getValue1());
	}

}

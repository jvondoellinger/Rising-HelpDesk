package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import org.springframework.http.ResponseEntity;

public final class HttpResponseMapper {

	/**
	 * @apiNote Atenção: Esse método NÃO garante que o valor seja serializavel! Garanta que o valor sendo passado será serializavel, senão, irá retornar uma exception!
	 */
	public ResponseEntity<?> fromResult(Result<?, String> result) {
		// Failure
		if (result.isFailure()) {
			return ResponseEntity.badRequest().body(result.getError());
		}

		// No value
		if (result.isVoid()) {
			return ResponseEntity.accepted().build();
		}

		// Has Value
		return ResponseEntity.ok(result.getValue());
	}
}

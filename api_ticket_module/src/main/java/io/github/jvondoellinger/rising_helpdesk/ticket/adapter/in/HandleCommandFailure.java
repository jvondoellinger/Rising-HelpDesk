package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import org.springframework.http.ResponseEntity;

public final class HandleCommandFailure {
	public static ResponseEntity<String> handleFailure(Result<?, String> result) {
		if (result.isFailure()) {
			return ResponseEntity.badRequest().body(result.getError());
		}
		return ResponseEntity.accepted().build();
	}
}

package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckEndpoint {
	@GetMapping
	public ResponseEntity<?> check() {
		return ResponseEntity.ok().build();
	}
}

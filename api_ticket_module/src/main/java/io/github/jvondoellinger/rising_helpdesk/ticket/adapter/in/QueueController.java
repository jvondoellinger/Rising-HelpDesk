package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandResult;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.CreateQueueCommandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
	private final CreateQueueCommandUseCase useCase;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateQueueCommand requestDTO) {
		var result = useCase.execute(requestDTO);

		if (result instanceof CommandResult.Success)
			return ResponseEntity.ok(((CommandResult.Success<QueueDetails>) result).value());
		else if (result instanceof CommandResult.Failure)
			return ResponseEntity.badRequest().body(((CommandResult.Failure<QueueDetails>) result).error().getMessage());
		return null;
	}
}

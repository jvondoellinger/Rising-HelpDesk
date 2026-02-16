package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.CreateQueueCommandUseCase;
import lombok.AllArgsConstructor;
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
	public QueueDetails create(@RequestBody CreateQueueCommand requestDTO) {
		return useCase.execute(requestDTO);
	}
}

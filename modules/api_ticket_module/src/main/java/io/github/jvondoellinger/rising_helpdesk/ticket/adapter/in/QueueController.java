package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.shared.adapters.HttpResultMapper;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
	private final QueueResponseMapper responseMapper;
	private final QueryBus queryBus;
	private final CommandBus commandBus;

	@PostMapping
	public ResponseEntity<?> createQueue(@RequestBody CreateQueueRequest request) {
		var result = commandBus.send(new CreateQueueCommand(
			   request.area(),
			   request.subarea()
		));
		return HttpResultMapper.from(result);
	}

	@DeleteMapping("/{queueId}")
	public ResponseEntity<?> deleteQueue(@PathVariable UUID queueId) {
		var result = commandBus.send(new RemoveQueueCommand(
			   queueId
		));
		return HttpResultMapper.from(result);
	}

	@PatchMapping("/area")
	public ResponseEntity<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
		var result = commandBus.send(new ChangeQueueAreaCommand(
			   request.id(),
			   request.area()
		));
		return HttpResultMapper.from(result);
	}

	@PatchMapping("/subarea")
	public ResponseEntity<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
		var result = commandBus.send(new ChangeQueueSubareaCommand(
			   request.id(),
			   request.subarea()
		));
		return HttpResultMapper.from(result);

	}


	// ! Get routes
	@GetMapping
	public ResponseEntity<?> findByPagination(@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "size", defaultValue = "100") int size) {
		var result = queryBus.send(new FindQueueByPaginationQuery(
			   page,
			   size
		));

		if (result.hasErrors()) {
			return HttpResultMapper.from(result);
		}

		var response = result
			   .map(responseMapper::from)
			   .getOrDefault(null);

		return HttpResultMapper.from(
			   ResultB.of(response)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindQueueByIdQuery(
			   id
		));

		if (result.hasErrors()) {
			return HttpResultMapper.from(result);
		}

		var response = result
			   .map(responseMapper::from)
			   .getOrDefault(null);


		return HttpResultMapper.from(
			   ResultB.of(response)
		);
	}
}

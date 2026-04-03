package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.ErrorResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DeleteQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.HandleCommandFailure.handleFailure;

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

		return handleFailure(result);
	}

	@DeleteMapping("/{queueId}")
	public ResponseEntity<?> deleteQueue(@PathVariable UUID queueId) {
		var result = commandBus.send(new DeleteQueueCommand(
			   queueId
		));

		return handleFailure(result);
	}

	@PatchMapping("/area")
	public ResponseEntity<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
		var result = commandBus.send(new ChangeQueueAreaCommand(
			   request.id(),
			   request.area()
		));

		return handleFailure(result);
	}

	@PatchMapping("/subarea")
	public ResponseEntity<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
		var result = commandBus.send(new ChangeQueueSubareaCommand(
			   request.id(),
			   request.subarea()
		));

		return handleFailure(result);
	}


	// ! Get routes
	@GetMapping
	public ResponseEntity<?> findByPagination(@RequestParam(value = "page", defaultValue = "0") int page,
									  @RequestParam(value = "size", defaultValue = "100") int size) {
		var result = queryBus.send(new FindQueueByPaginationQuery(
			   page,
			   size
		));

		if (result.isFailure()) {
			return ResponseEntity.badRequest().body(new ErrorResponse(
				   result.getError()
			));
		}

		var response = responseMapper.from(result.getValue());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindQueueByIdQuery(
			   id
		));

		if (result.isFailure()) {
			return ResponseEntity.badRequest().body(new ErrorResponse(
				   result.getError()
			));
		}

		var response = responseMapper.from(result.getValue());

		return ResponseEntity.ok(response);
	}
}

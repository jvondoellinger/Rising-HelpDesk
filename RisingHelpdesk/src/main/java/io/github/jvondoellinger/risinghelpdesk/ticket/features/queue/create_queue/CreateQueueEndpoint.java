package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.create_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class CreateQueueEndpoint {
	private final CreateQueueHandler handler;

	@PostMapping
	ResponseEntity<?> createQueueHandler(@RequestBody CreateQueueRequest request) {
		var cmd = new CreateQueue(request.area(), request.subarea());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}

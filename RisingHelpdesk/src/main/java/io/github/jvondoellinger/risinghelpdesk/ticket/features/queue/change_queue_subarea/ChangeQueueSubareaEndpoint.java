package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_subarea;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class ChangeQueueSubareaEndpoint {
	private final ChangeQueueSubareaHandler handler;

	@PatchMapping
	ResponseEntity<?> changeQueueSubareaHandler(@RequestBody ChangeQueueSubareaRequest request) {
		var cmd = new ChangeQueueSubarea(request.id(), request.subarea());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}

package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_area;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class ChangeQueueAreaEndpoint {
	private final ChangeQueueAreaHandler handler;

	@PatchMapping("/area")
	ResponseEntity<?> changeQueueAreaHandler(@RequestBody ChangeQueueAreaRequest request) {
		var cmd = new ChangeQueueArea(request.id(), request.area());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}

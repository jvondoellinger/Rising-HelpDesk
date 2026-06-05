package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.create_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

public record CreateQueue(
		String area,
		String subarea
)  implements Command {
}

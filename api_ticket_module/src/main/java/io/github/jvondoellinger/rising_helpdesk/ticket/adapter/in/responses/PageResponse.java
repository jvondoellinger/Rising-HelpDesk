package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.util.List;

public record PageResponse<T>(
	   List<T> items,
	   int page,
	   int size,
	   int totalPages
) {
}

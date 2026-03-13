package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import java.util.List;

public record Pagination<T>(
	   List<T> items,
	   int page,
	   int size,
	   int totalPages
) {
	public boolean empty() {
		return  items == null ||
				items.isEmpty() ||
				size == 0 ||
				totalPages == 0;
	}
}

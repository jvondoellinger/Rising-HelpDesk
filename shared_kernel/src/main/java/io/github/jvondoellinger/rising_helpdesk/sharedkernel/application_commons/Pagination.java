package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import java.util.List;

public record Pagination<T>(
	   List<T> items,
	   int page,
	   int size,
	   long totalItems
) {
	public int totalPages() {
		return (int) Math.ceil((double) totalItems / size);
	}

	public boolean empty() {
		if (items == null || items.isEmpty()) return true;
		if (size == 0 || totalItems == 0) return true;

		return false;
	}
}

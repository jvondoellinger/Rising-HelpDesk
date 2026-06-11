package io.github.jvondoellinger.risinghelpdesk.shared.repository;

import java.util.ArrayList;
import java.util.List;

public record Pagination<T>(
	   List<T> items,
	   int page,
	   int size,
	   int totalPages
) {
	public static <T> Pagination<T> of(List<T> items,
								int page,
								int totalPages) {
		return new Pagination<>(items, page, items.size(), totalPages);
	}

	public static <T> Pagination<T> empty() {
		return new Pagination<>(new ArrayList<>(), 0, 0, 0);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
}

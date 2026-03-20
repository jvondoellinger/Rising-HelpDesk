package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
	private final List<T> items;
	private final int page;
	private final int size;
	private final int totalPages;
	private Pagination(List<T> items,
				   int page,
				   int size,
				   int totalPages) {
		this.items = items;
		this.page = page;
		this.size = size;
		this.totalPages = totalPages;
	}
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

	public List<T> items() {
		return items;
	}

	public int page() {
		return page;
	}

	public int size() {
		return size;
	}

	public int totalPages() {
		return totalPages;
	}
}

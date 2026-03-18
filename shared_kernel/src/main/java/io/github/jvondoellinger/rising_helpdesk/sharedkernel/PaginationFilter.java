package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

public class PaginationFilter {
	private final int size;
	private final int page;

	private PaginationFilter(int size, int page) {
		this.size = size;
		this.page = page;
	}

	public static PaginationFilter of(int size, int page) {
		return new PaginationFilter(size, page);
	}

	public int page() {
		return page;
	}

	public int size() {
		return size;
	}
}

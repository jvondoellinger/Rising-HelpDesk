package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

public class QueryFilter {
	private final int size;
	private final int page;

	private QueryFilter(int size, int page) {
		this.size = size;
		this.page = page;
	}

	public static QueryFilter of(int size, int page) {
		return new QueryFilter(size, page);
	}

	public int page() {
		return page;
	}

	public int size() {
		return size;
	}
}

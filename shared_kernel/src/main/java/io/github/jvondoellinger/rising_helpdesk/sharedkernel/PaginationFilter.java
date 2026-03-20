package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

/**
 * The type Pagination filter.
 */
public class PaginationFilter {
	private final int page;
	private final int size;

	private PaginationFilter(int page, int size) {
		this.page = page;
		this.size = size;
	}


	public static PaginationFilter of(int page, int size) {
		if (page < 0) throw new RuntimeException("The page can't be smaller than 0.");
		if (size < 0) throw new RuntimeException("The size can't be smaller than 0.");

		return new PaginationFilter(page, size);
	}

	public int page() {
		return page;
	}
	public int size() {
		return size;
	}
}

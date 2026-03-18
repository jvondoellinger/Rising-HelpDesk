package io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;

public interface CrudsRepository<T, ID> {
	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T queryById(ID id);

	boolean existsById(ID id);

	/**
	 *
	 * @param filter
	 * @return Readonly list
	 */
	Pagination<T> query(PaginationFilter filter);
	long total();
}

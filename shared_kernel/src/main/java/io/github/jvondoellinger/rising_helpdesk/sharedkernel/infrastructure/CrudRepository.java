package io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;

import java.util.Optional;

public interface CrudsRepository<T, ID> {
	void save(T entity);
	void update(T entity);
	void delete(T entity);
	Optional<T> findById(ID id);

	boolean existsById(ID id);

	/**
	 *
	 * @param filter
	 * @return Readonly list
	 */
	Pagination<T> findByPagination(PaginationFilter filter);
	long total();
}

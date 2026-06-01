package io.github.jvondoellinger.rising_helpdesk.shared.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;

import java.util.Optional;

public interface CrudRepository<T, ID> {
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

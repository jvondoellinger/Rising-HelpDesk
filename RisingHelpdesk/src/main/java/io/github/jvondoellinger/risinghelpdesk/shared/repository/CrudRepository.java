package io.github.jvondoellinger.risinghelpdesk.shared.repository;

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

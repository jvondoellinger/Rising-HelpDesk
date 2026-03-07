package io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import java.util.List;

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
	List<T> query(QueryFilter filter);
	long total();
}

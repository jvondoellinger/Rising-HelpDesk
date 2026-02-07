package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;

public interface QueryUseCase<O> {
	O queryById(DomainId Id);
	Pagination<O> queryByPagination(int page, int size);
}
